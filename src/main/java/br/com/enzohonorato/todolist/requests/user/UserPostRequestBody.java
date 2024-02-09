package br.com.enzohonorato.todolist.requests.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserPostRequestBody {
    private String username;
    private String password;
    private UserRole role;
}
