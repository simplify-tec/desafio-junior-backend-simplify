package br.com.enzohonorato.todolist.requests.task;

public enum TaskPriority {
    LOW("LOW"),
    MEDIUM("MEDIUM"),
    HIGH("HIGH");

    public final String NAME;

    TaskPriority(String name) {
        this.NAME = name;
    }
}
