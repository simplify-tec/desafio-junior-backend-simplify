package br.com.enzohonorato.todolist.integration;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.TaskRepository;
import br.com.enzohonorato.todolist.repository.UserRepository;
import br.com.enzohonorato.todolist.requests.task.*;
import br.com.enzohonorato.todolist.util.task.TaskCreator;
import br.com.enzohonorato.todolist.util.task.TaskPostRequestBodyCreator;
import br.com.enzohonorato.todolist.util.task.TaskPutRequestBodyCreator;
import br.com.enzohonorato.todolist.util.user.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.Base64;
import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TaskControllerIT {
    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TaskRepository taskRepository;

    User userToBeSaved;
    User savedUser;
    Task taskToBeSaved;
    Task savedTask;

    String credentials;
    String encodedCredentials;

    @BeforeEach
    void setUp() {
        userToBeSaved = UserCreator.createUserToBeSaved();
        savedUser = userRepository.save(userToBeSaved);

        taskToBeSaved = TaskCreator.createTaskToBeSaved();
        taskToBeSaved.setUser(savedUser);
        savedTask = taskRepository.save(taskToBeSaved);

        credentials = "Enzo" + ":" + "123";
        encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());
    }

    @Test
    void findByUser_ReturnsListOfTaskGetResponseBody_WhenUserHasTask() {

        EntityExchangeResult<List<TaskGetResponseBody>> entityExchangeResult = webTestClient
                .get().uri("/tasks/list")
                .header("Authorization", "Basic " + encodedCredentials)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskGetResponseBody.class)
                .returnResult();

        Assertions.assertThat(entityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entityExchangeResult.getResponseBody())
                .isNotNull()
                .isNotEmpty();
    }

    @Test
    void findByPriority_ReturnsListOfTaskGetResponseBodyWithGivenPriority_WhenUserHasTaskWithGivenPriority() {
        EntityExchangeResult<List<TaskGetResponseBody>> entityExchangeResult = webTestClient
                .get().uri("/tasks/list/priority?priority=LOW")
                .header("Authorization", "Basic " + encodedCredentials)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskGetResponseBody.class)
                .returnResult();

        Assertions.assertThat(entityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entityExchangeResult.getResponseBody())
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(entityExchangeResult.getResponseBody().get(0).getPriority()).isEqualTo(TaskPriority.LOW);
    }

    @Test
    void findByPriority_ReturnsEmptyListOfTaskGetResponseBody_WhenUserHasNoTaskWithGivenPriority() {
        EntityExchangeResult<List<TaskGetResponseBody>> entityExchangeResult = webTestClient
                .get().uri("/tasks/list/priority?priority=HIGH")
                .header("Authorization", "Basic " + encodedCredentials)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskGetResponseBody.class)
                .returnResult();

        Assertions.assertThat(entityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entityExchangeResult.getResponseBody())
                .isNotNull()
                .isEmpty();
    }

    @Test
    void findByDone_ReturnsListOfTaskGetResponseBody_WhenUserHasTaskWithGivenDone() {
        EntityExchangeResult<List<TaskGetResponseBody>> entityExchangeResult = webTestClient
                .get().uri("/tasks/list/done?done=true")
                .header("Authorization", "Basic " + encodedCredentials)
                .exchange()
                .expectStatus().isOk()
                .expectBodyList(TaskGetResponseBody.class)
                .returnResult();

        Assertions.assertThat(entityExchangeResult.getStatus()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(entityExchangeResult.getResponseBody())
                .isNotNull()
                .isNotEmpty();
        Assertions.assertThat(entityExchangeResult.getResponseBody().get(0).getDone()).isEqualTo(true);
    }

    @Test
    void save_ReturnsSavedTask_WhenSuccessful() {
        TaskPostRequestBody taskPostRequestBody = TaskPostRequestBodyCreator.createTaskPostRequestBodyToBeSaved();

        EntityExchangeResult<SavedTaskResponseBody> entityExchangeResult = webTestClient
                .post().uri("/tasks")
                .header("Authorization", "Basic " + encodedCredentials)
                .header("Content-Type", "application/json")
                .bodyValue(taskPostRequestBody)
                .exchange()
                .expectStatus().isCreated()
                .expectBody(SavedTaskResponseBody.class)
                .returnResult();

        Assertions.assertThat(entityExchangeResult.getStatus()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(entityExchangeResult.getResponseBody().getId()).isNotNull();

        Assertions.assertThat(entityExchangeResult.getResponseBody().getName()).isEqualTo(taskPostRequestBody.getName());
        Assertions.assertThat(entityExchangeResult.getResponseBody().getDescription()).isEqualTo(taskPostRequestBody.getDescription());
        Assertions.assertThat(entityExchangeResult.getResponseBody().getPriority()).isEqualTo(taskPostRequestBody.getPriority());
        Assertions.assertThat(entityExchangeResult.getResponseBody().getDone()).isEqualTo(taskPostRequestBody.getDone());
    }

    @Test
    void delete_ReturnsStatusCode204_WhenSuccessful() {
        webTestClient
                .delete().uri("/tasks/{id}", savedTask.getId())
                .header("Authorization", "Basic " + encodedCredentials)
                .exchange()
                .expectStatus().isNoContent();
    }

    @Test
    void update_ReturnsStatusCode204_WhenSuccessful() {
        TaskPutRequestBody taskPutRequestBody = TaskPutRequestBodyCreator.createTaskPutRequestBodyToBeUpdated();

        webTestClient
                .put().uri("/tasks")
                .header("Authorization", "Basic " + encodedCredentials)
                .header("Content-Type", "application/json")
                .bodyValue(taskPutRequestBody)
                .exchange()
                .expectStatus().isNoContent();
    }

}
