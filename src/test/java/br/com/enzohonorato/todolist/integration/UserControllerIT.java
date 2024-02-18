package br.com.enzohonorato.todolist.integration;

import br.com.enzohonorato.todolist.domain.User;
import br.com.enzohonorato.todolist.repository.UserRepository;
import br.com.enzohonorato.todolist.requests.user.UserPostRequestBody;
import br.com.enzohonorato.todolist.util.user.UserCreator;
import br.com.enzohonorato.todolist.util.user.UserPostRequestBodyCreator;
import org.hibernate.annotations.DialectOverride;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.client.RestTemplate;

import java.util.Base64;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class UserControllerIT {
    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WebTestClient webTestClient;

    private RestTemplate restTemplate;

    User adminUserToBeSaved;

    String credentials;
    String encodedCredentials;

    @BeforeEach
    void setUp() {
        adminUserToBeSaved = UserCreator.createAdminUserToBeSaved();
        userRepository.save(adminUserToBeSaved);

        credentials = "Admin" + ":" + "123";
        encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes());

        restTemplate = new RestTemplate();
    }

    @Test
    void save_ReturnsSavedUser_WhenSuccessful() {
        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBodyToBeSaved();

        webTestClient
                .post().uri("/users")
                .header("Authorization", "Basic " + encodedCredentials)
                .header("Content-Type", "application/json")
                .bodyValue(userPostRequestBody)
                .exchange()
                .expectStatus().isCreated();

    }


    // Apenas para mostrar como seria com o RestTemplate
//    @Test
//    void save_ReturnsSavedUser_WhenSuccessful2() {
//        UserPostRequestBody userPostRequestBody = UserPostRequestBodyCreator.createUserPostRequestBodyToBeSaved();
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setBasicAuth("Admin", "123");
//        HttpEntity<UserPostRequestBody> request = new HttpEntity<>(userPostRequestBody, headers);
//
//        ResponseEntity<User> responseEntity = restTemplate.exchange("http://localhost:{port}/users", HttpMethod.POST, request, User.class, port);
//    }
}
