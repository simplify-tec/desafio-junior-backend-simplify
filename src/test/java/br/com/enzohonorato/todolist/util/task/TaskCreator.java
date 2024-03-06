package br.com.enzohonorato.todolist.util.task;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.util.user.UserCreator;

public class TaskCreator {
    public static Task createTaskToBeSaved() {
        return Task.builder()
                .name("Lavar carro")
                .description("Lavar todo o carro")
                .priority("LOW")
                .done(true).build();
    }

    public static Task createSavedTask() {
        return Task.builder()
                .id(1L)
                .name("Lavar carro")
                .description("Lavar todo o carro")
                .priority("LOW")
                .done(true)
                .user(UserCreator.createSavedUser()).build();
    }
}
