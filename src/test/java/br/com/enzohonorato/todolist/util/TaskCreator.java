package br.com.enzohonorato.todolist.util;

import br.com.enzohonorato.todolist.domain.Task;

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
