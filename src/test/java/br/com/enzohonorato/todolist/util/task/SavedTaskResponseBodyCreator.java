package br.com.enzohonorato.todolist.util.task;

import br.com.enzohonorato.todolist.requests.task.SavedTaskResponseBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;

public class SavedTaskResponseBodyCreator {
    public static SavedTaskResponseBody createSavedTaskResponseBody() {
        return SavedTaskResponseBody.builder()
                .id(1L)
                .name("Lavar carro")
                .description("Lavar todo o carro")
                .priority(TaskPriority.LOW)
                .done(true).build();
    }
}
