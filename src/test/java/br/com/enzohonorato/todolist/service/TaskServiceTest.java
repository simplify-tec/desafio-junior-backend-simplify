package br.com.enzohonorato.todolist.service;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.TaskRepository;
import br.com.enzohonorato.todolist.requests.task.*;
import br.com.enzohonorato.todolist.util.task.TaskCreator;
import br.com.enzohonorato.todolist.util.task.TaskPostRequestBodyCreator;
import br.com.enzohonorato.todolist.util.task.TaskPutRequestBodyCreator;
import br.com.enzohonorato.todolist.util.user.UserCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {
    @Mock
    private TaskRepository taskRepository;
    @Mock
    private SecurityContext securityContext;
//    @Mock
//    private ModelMapper modelMapper;

    @InjectMocks
    private TaskService taskService;

    TaskPutRequestBody taskPutRequestBodyToBeUpdated;
    TaskPostRequestBody taskPostRequestBodyToBeSaved;
    Task taskToBeSaved;
    Task savedTask;
    User savedUser;

    @BeforeEach
    void setUp() {
        savedUser = UserCreator.createSavedUser();
        taskPostRequestBodyToBeSaved = TaskPostRequestBodyCreator.createTaskPostRequestBodyToBeSaved();
        taskToBeSaved = TaskCreator.createTaskToBeSaved();
        savedTask = TaskCreator.createSavedTask();
        taskPutRequestBodyToBeUpdated = TaskPutRequestBodyCreator.createTaskPutRequestBodyToBeUpdated();

        BDDMockito.lenient().when(taskRepository.save(ArgumentMatchers.any(Task.class))).thenReturn(savedTask);

        SecurityContextHolder.setContext(securityContext);
        BDDMockito.when(securityContext.getAuthentication()).thenReturn(new UsernamePasswordAuthenticationToken(savedUser, null));

        //BDDMockito.when(modelMapper.map(ArgumentMatchers.any(TaskPostRequestBody.class), ArgumentMatchers.any())).thenReturn(taskToBeSaved);

        BDDMockito.lenient().doNothing().when(taskRepository).delete(ArgumentMatchers.any(Task.class));

        BDDMockito.lenient().when(taskRepository.findByIdAndUser(ArgumentMatchers.anyLong(), ArgumentMatchers.anyLong())).thenReturn(savedTask);

        BDDMockito.lenient().when(taskRepository.findByUser(ArgumentMatchers.any(User.class))).thenReturn(List.of(savedTask));

        BDDMockito.lenient().when(taskRepository.findByUserAndPriority(ArgumentMatchers.anyLong(), ArgumentMatchers.anyString())).thenReturn(List.of(savedTask));

        BDDMockito.lenient().when(taskRepository.findByUserAndDone(ArgumentMatchers.anyLong(), ArgumentMatchers.anyInt())).thenReturn(List.of(savedTask));
    }

    @Test
    void save_ReturnsSavedTask_WhenSuccessful() {
        SavedTaskResponseBody savedTaskResponseBody = taskService.save(taskPostRequestBodyToBeSaved);

        Assertions.assertThat(savedTaskResponseBody).isNotNull();
        Assertions.assertThat(savedTaskResponseBody.getId()).isNotNull();

        Assertions.assertThat(savedTaskResponseBody.getName()).isEqualTo(taskPostRequestBodyToBeSaved.getName());
        Assertions.assertThat(savedTaskResponseBody.getDescription()).isEqualTo(taskPostRequestBodyToBeSaved.getDescription());
        Assertions.assertThat(savedTaskResponseBody.getPriority()).isEqualTo(taskPostRequestBodyToBeSaved.getPriority());
        Assertions.assertThat(savedTaskResponseBody.getDone()).isEqualTo(taskPostRequestBodyToBeSaved.getDone());
    }

    @Test
    void delete_RemovesTaskAndReturnsNothing_WhenSuccessful() {
        Assertions.assertThatCode(() -> taskService.delete(savedTask.getId()))
                .doesNotThrowAnyException();
    }

    @Test
    void update_UpdatesTaskAndReturnsNothing_WhenSuccessful() {
        Assertions.assertThatCode(() -> taskService.update(taskPutRequestBodyToBeUpdated))
                .doesNotThrowAnyException();
    }

    @Test
    void findByUser_ReturnsListOfTaskGetResponseBody_WhenSuccessful() {
        List<TaskGetResponseBody> taskGetResponseBodyList = taskService.findByUser();

        Assertions.assertThat(taskGetResponseBodyList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void findByUserAndPriority_ReturnsListOfTaskGetResponseBody_WhenSuccessful() {
        List<TaskGetResponseBody> taskGetResponseBodyList = taskService.findByUserAndPriority(TaskPriority.LOW);

        Assertions.assertThat(taskGetResponseBodyList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }

    @Test
    void findByUserAndDone_ReturnsListOfTaskGetResponseBody_WhenSuccessful() {
        List<TaskGetResponseBody> taskGetResponseBodyList = taskService.findByUserAndDone(true);

        Assertions.assertThat(taskGetResponseBodyList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
    }










}
