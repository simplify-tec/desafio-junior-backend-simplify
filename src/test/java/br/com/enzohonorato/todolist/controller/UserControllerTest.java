package br.com.enzohonorato.todolist.controller;

import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.requests.user.UserPostRequestBody;
import br.com.enzohonorato.todolist.service.UserService;
import br.com.enzohonorato.todolist.util.user.UserCreator;
import br.com.enzohonorato.todolist.util.user.UserPostRequestBodyCreator;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    UserPostRequestBody userPostRequestBody;
    User savedUser;

    @BeforeEach
    void setUp() {
        userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBodyToBeSaved();
        savedUser = UserCreator.createSavedUser();

        BDDMockito.when(userService.save(ArgumentMatchers.any(UserPostRequestBody.class))).thenReturn(savedUser);
    }

    @Test
    void save_ReturnsSavedUserWithinResponseEntityWithStatusCode201_WhenSuccessful() {
        ResponseEntity<User> responseEntity = userController.save(userPostRequestBody);

        Assertions.assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Assertions.assertThat(responseEntity.getBody()).isNotNull();

        Assertions.assertThat(responseEntity.getBody().getId()).isNotNull();
        Assertions.assertThat(responseEntity.getBody().getUsername()).isEqualTo(userPostRequestBody.getUsername());
        Assertions.assertThat(responseEntity.getBody().getRole()).isEqualTo(userPostRequestBody.getRole().NAME);
    }
}
