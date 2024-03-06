package br.com.enzohonorato.todolist.service;

import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.UserRepository;
import br.com.enzohonorato.todolist.requests.user.UserPostRequestBody;
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
import org.springframework.security.core.userdetails.UserDetails;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    UserPostRequestBody userPostRequestBodyToBeSaved;
    User savedUser;

    @BeforeEach
    void setUp() {
        userPostRequestBodyToBeSaved = UserPostRequestBodyCreator.createUserPostRequestBodyToBeSaved();
        savedUser = UserCreator.createSavedUser();

        BDDMockito.lenient().when(userRepository.save(ArgumentMatchers.any(User.class))).thenReturn(savedUser);

        BDDMockito.lenient().when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn((UserDetails) savedUser);
    }

    @Test
    void save_ReturnsSavedUser_WhenSuccessful() {
        User user = userService.save(userPostRequestBodyToBeSaved);

        Assertions.assertThat(user).isNotNull();
        Assertions.assertThat(user.getId()).isNotNull();

        Assertions.assertThat(user.getUsername()).isEqualTo(userPostRequestBodyToBeSaved.getUsername());
        Assertions.assertThat(user.getRole()).isEqualTo(userPostRequestBodyToBeSaved.getRole().NAME);

        Assertions.assertThat(user.getPassword()).isNotEqualTo(userPostRequestBodyToBeSaved.getPassword());
    }

    @Test
    void loadUserByUsername_ReturnsUserDetails_WhenSuccessful() {
        UserDetails userDetails = userService.loadUserByUsername(savedUser.getUsername());

        Assertions.assertThat(userDetails).isNotNull();

        Assertions.assertThat(userDetails.getUsername()).isEqualTo(savedUser.getUsername());
        Assertions.assertThat(userDetails.getPassword()).isEqualTo(savedUser.getPassword());
    }
















}
