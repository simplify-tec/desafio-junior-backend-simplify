package br.com.enzohonorato.todolist.requests.user;

public enum UserRole {
    USER("USER"),
    ADMIN("ADMIN");

    public final String NAME;

    UserRole(String name) {
        this.NAME = name;
    }
}
