package br.com.enzohonorato.todolist.util;

import br.com.enzohonorato.todolist.requests.user.UserPostRequestBody;
import br.com.enzohonorato.todolist.requests.user.UserRole;

public class UserPostRequestBodyCreator {

    public static UserPostRequestBody createUserPostRequestBodyToBeSaved() {
        return UserPostRequestBody.builder()
                .username("Enzo")
                .password("123")
                .role(UserRole.USER).build();
    }

}
