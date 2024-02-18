package br.com.enzohonorato.todolist.util.user;

import br.com.enzohonorato.todolist.domain.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserCreator {
    private static final String ENCODED_PASSWORD = new BCryptPasswordEncoder().encode("123");

    public static User createSavedUser() {
        return User.builder()
                .id(1L)
                .username("Enzo")
                .password(ENCODED_PASSWORD)
                .role("USER").build();
    }

    public static User createUserToBeSaved() {
        return User.builder()
                .username("Enzo")
                .password(ENCODED_PASSWORD)
                .role("USER").build();
    }

    public static User createAdminUserToBeSaved() {
        return User.builder()
                .username("Admin")
                .password(ENCODED_PASSWORD)
                .role("ADMIN").build();
    }
}
