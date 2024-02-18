package br.com.enzohonorato.todolist.util.task;

import br.com.enzohonorato.todolist.requests.task.TaskPostRequestBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;

public class TaskPostRequestBodyCreator {
    public static TaskPostRequestBody createTaskPostRequestBodyToBeSaved() {
        return TaskPostRequestBody.builder()
                .name("Lavar carro")
                .description("Lavar todo o carro")
                .priority(TaskPriority.LOW)
                .done(true).build();
    }
}
