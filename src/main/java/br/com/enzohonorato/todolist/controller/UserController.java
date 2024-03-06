package br.com.enzohonorato.todolist.controller;

import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.requests.user.UserPostRequestBody;
import br.com.enzohonorato.todolist.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @Operation(summary = "Salvar um novo usuário de acordo com o conteúdo do body da requisição", tags = {"users - ADMIN"})
    @PostMapping
    public ResponseEntity<User> save(@RequestBody UserPostRequestBody userPostRequestBody) {
        return new ResponseEntity<>(userService.save(userPostRequestBody), HttpStatus.CREATED);
    }
}
