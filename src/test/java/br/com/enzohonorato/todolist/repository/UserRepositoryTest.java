package br.com.enzohonorato.todolist.repository;

import br.com.enzohonorato.todolist.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void save_ReturnsSavedUser_WhenSuccessful() {
        //Arrange
        User user = User.builder()
                .username("Enzo")
                .password(new BCryptPasswordEncoder().encode("123"))
                .role("USER").build();

        //Act
        User savedUser = userRepository.save(user);

        //Assert
        Assertions.assertThat(savedUser).isNotNull();
        Assertions.assertThat(savedUser.getId()).isNotNull();

        Assertions.assertThat(savedUser.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(savedUser.getPassword()).isEqualTo(user.getPassword());
        Assertions.assertThat(savedUser.getRole()).isEqualTo(user.getRole());
    }

    @Test
    void findByUsername_ReturnsUserDetails_WhenUserIsFound(){
        User user = User.builder()
                .username("Enzo")
                .password(new BCryptPasswordEncoder().encode("123"))
                .role("USER").build();

        userRepository.save(user);
        User userFound = (User) userRepository.findByUsername(user.getUsername());

        Assertions.assertThat(userFound).isNotNull();

        Assertions.assertThat(userFound.getUsername()).isEqualTo(user.getUsername());
        Assertions.assertThat(userFound.getPassword()).isEqualTo(user.getPassword());
        Assertions.assertThat(userFound.getRole()).isEqualTo(user.getRole());
    }


}
