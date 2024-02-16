package br.com.enzohonorato.todolist.requests.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserPostRequestBody {
    private String username;
    private String password;
    private UserRole role;
}
