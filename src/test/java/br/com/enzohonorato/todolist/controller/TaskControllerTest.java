package br.com.enzohonorato.todolist.controller;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.requests.task.TaskGetResponseBody;
import br.com.enzohonorato.todolist.requests.task.TaskPostRequestBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;
import br.com.enzohonorato.todolist.requests.task.TaskPutRequestBody;
import br.com.enzohonorato.todolist.service.TaskService;
import br.com.enzohonorato.todolist.util.TaskCreator;
import br.com.enzohonorato.todolist.util.TaskGetResponseBodyCreator;
import br.com.enzohonorato.todolist.util.TaskPostRequestBodyCreator;
import br.com.enzohonorato.todolist.util.TaskPutRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class TaskControllerTest {
    @Mock
    private TaskService taskService;

    @InjectMocks
    private TaskController taskController;

    TaskGetResponseBody taskGetResponseBody;
    Task savedTask;
    TaskPostRequestBody taskPostRequestBodyToBeSaved;
    TaskPutRequestBody taskPutRequestBodyToBeUpdated;


    @BeforeEach
    void setUp() {
        taskGetResponseBody = TaskGetResponseBodyCreator.createTaskGetResponseBody();
        savedTask = TaskCreator.createSavedTask();
        taskPostRequestBodyToBeSaved = TaskPostRequestBodyCreator.createTaskPostRequestBodyToBeSaved();
        taskPutRequestBodyToBeUpdated = TaskPutRequestBodyCreator.createTaskPutRequestBodyToBeUpdated();

        BDDMockito.lenient().when(taskService.findByUser()).thenReturn(List.of(taskGetResponseBody));

        BDDMockito.lenient().when(taskService.findByUserAndPriority(ArgumentMatchers.any(TaskPriority.class))).thenReturn(List.of(taskGetResponseBody));

        BDDMockito.lenient().when(taskService.findByUserAndDone(ArgumentMatchers.anyBoolean())).thenReturn(List.of(taskGetResponseBody));

        BDDMockito.lenient().when(taskService.save(ArgumentMatchers.any(TaskPostRequestBody.class))).thenReturn(savedTask);

        BDDMockito.lenient().doNothing().when(taskService).delete(ArgumentMatchers.anyLong());

        BDDMockito.lenient().doNothing().when(taskService).update(ArgumentMatchers.any(TaskPutRequestBody.class));
    }

    @Test
    void findByUser_ReturnsListOfTaskGetResponseBodyWithinResponseEntityWithStatusCode200_WhenSuccessful() {
        // Act
        ResponseEntity<List<TaskGetResponseBody>> responseEntity = taskController.findByUser();

        // Assert
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void findByPriority_ReturnsListOfTaskGetResponseBodyWithinResponseEntityWithStatusCode200_WhenSuccessful() {
        // Act
        ResponseEntity<List<TaskGetResponseBody>> responseEntity = taskController.findByPriority(TaskPriority.LOW);

        // Assert
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void findByDone_ReturnsListOfTaskGetResponseBodyWithinResponseEntityWithStatusCode200_WhenSuccessful() {
        // Act
        ResponseEntity<List<TaskGetResponseBody>> responseEntity = taskController.findByDone(true);

        // Assert
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        Assertions.assertThat(responseEntity.getBody())
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void save_ReturnsTaskWithinResponseEntityWithStatusCode201_WhenSuccessful() {
        // Act
        ResponseEntity<Task> responseEntity = taskController.save(taskPostRequestBodyToBeSaved);

        // Assert
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();

        Assertions.assertThat(responseEntity.getBody().getId()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getName()).isEqualTo(taskPostRequestBodyToBeSaved.getName());
        Assertions.assertThat(responseEntity.getBody().getDescription()).isEqualTo(taskPostRequestBodyToBeSaved.getDescription());
        Assertions.assertThat(responseEntity.getBody().getPriority()).isEqualTo(taskPostRequestBodyToBeSaved.getPriority().NAME);
        Assertions.assertThat(responseEntity.getBody().getDone()).isEqualTo(taskPostRequestBodyToBeSaved.getDone());
    }

    @Test
    void delete_ReturnsResponseEntityWithStatusCode204_WhenSuccessful() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Void> responseEntity = taskController.delete(id);

        // Assert
        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }

    @Test
    void update_ReturnsResponseEntityWithStatusCode204_WhenSuccessful() {
        // Act
        ResponseEntity<Void> responseEntity = taskController.update(taskPutRequestBodyToBeUpdated);

        // Assert
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
}
