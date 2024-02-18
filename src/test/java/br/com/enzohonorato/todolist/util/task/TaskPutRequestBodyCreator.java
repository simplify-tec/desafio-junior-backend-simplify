package br.com.enzohonorato.todolist.util.task;

import br.com.enzohonorato.todolist.requests.task.TaskPriority;
import br.com.enzohonorato.todolist.requests.task.TaskPutRequestBody;

public class TaskPutRequestBodyCreator {
    public static TaskPutRequestBody createTaskPutRequestBodyToBeUpdated() {
        return TaskPutRequestBody.builder()
                .id(1L)
                .name("Lavar a casa")
                .description("Lavar toda a casa")
                .priority(TaskPriority.LOW)
                .done(true).build();
    }
}
