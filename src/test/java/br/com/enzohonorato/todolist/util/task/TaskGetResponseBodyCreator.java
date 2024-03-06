package br.com.enzohonorato.todolist.util.task;

import br.com.enzohonorato.todolist.requests.task.TaskGetResponseBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;

public class TaskGetResponseBodyCreator {
    public static TaskGetResponseBody createTaskGetResponseBody() {
        return TaskGetResponseBody.builder()
                .id(1L)
                .name("Lavar carro")
                .description("Lavar todo o carro")
                .priority(TaskPriority.LOW)
                .done(true).build();
    }
}
