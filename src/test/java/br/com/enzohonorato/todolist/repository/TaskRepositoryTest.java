package br.com.enzohonorato.todolist.repository;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class TaskRepositoryTest {
    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private UserRepository userRepository;

    User userToBeSaved;
    Task taskToBeSaved;
    Task taskToBeSaved2;

    @BeforeEach
    public void setUp() {
        userToBeSaved = User.builder()
                .username("Enzo")
                .password(new BCryptPasswordEncoder().encode("123"))
                .role("USER").build();

        taskToBeSaved = Task.builder()
                .name("Lavar o carro")
                .description("Lavar todo o carro")
                .priority("LOW")
                .done(false).build();

        taskToBeSaved2 = Task.builder()
                .name("Lavar a casa")
                .description("Lavar toda a casa")
                .priority("LOW")
                .done(false).build();
    }

    @Test
    void save_ReturnsSavedTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        Task savedTask = taskRepository.save(taskToBeSaved);

        Assertions.assertThat(savedTask).isNotNull();
        Assertions.assertThat(savedTask.getId()).isNotNull();

        Assertions.assertThat(savedTask.getName()).isEqualTo(taskToBeSaved.getName());
        Assertions.assertThat(savedTask.getDescription()).isEqualTo(taskToBeSaved.getDescription());
        Assertions.assertThat(savedTask.getPriority()).isEqualTo(taskToBeSaved.getPriority());
        Assertions.assertThat(savedTask.getDone()).isEqualTo(taskToBeSaved.getDone());
        Assertions.assertThat(savedTask.getUser()).isEqualTo(taskToBeSaved.getUser());
    }

    @Test
    void delete_RemovesTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        Task savedTask = taskRepository.save(taskToBeSaved);

        taskRepository.delete(savedTask);

        Optional<Task> taskOptional = taskRepository.findById(savedTask.getId());

        Assertions.assertThat(taskOptional).isEmpty();
    }

    @Test
    void findByUser_ReturnsListOfTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        taskToBeSaved2.setUser(savedUser);

        taskRepository.save(taskToBeSaved);
        taskRepository.save(taskToBeSaved2);

        List<Task> taskList = taskRepository.findByUser(taskToBeSaved.getUser());

        Assertions.assertThat(taskList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2);
        Assertions.assertThat(taskList.get(0).getUser()).isEqualTo(taskToBeSaved.getUser());
        Assertions.assertThat(taskList.get(1).getUser()).isEqualTo(taskToBeSaved.getUser());
    }

    @Test
    void findByIdAndUser_ReturnsTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        Task savedTask = taskRepository.save(taskToBeSaved);

        Task foundTask = taskRepository.findByIdAndUser(savedTask.getId(), taskToBeSaved.getUser().getId());

        Assertions.assertThat(foundTask).isNotNull();
        Assertions.assertThat(foundTask.getId()).isEqualTo(savedTask.getId());
        Assertions.assertThat(foundTask.getUser()).isEqualTo(taskToBeSaved.getUser());
    }

    @Test
    void findByUserAndPriority_ReturnsListOfTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        taskRepository.save(taskToBeSaved);

        List<Task> taskList = taskRepository.findByUserAndPriority(taskToBeSaved.getUser().getId(), taskToBeSaved.getPriority());

        Assertions.assertThat(taskList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(taskList.get(0).getUser()).isEqualTo(taskToBeSaved.getUser());
        Assertions.assertThat(taskList.get(0).getPriority()).isEqualTo(taskToBeSaved.getPriority());
    }

    @Test
    void findByUserAndDone_ReturnsListOfTask_WhenSuccessful() {
        User savedUser = userRepository.save(userToBeSaved);
        taskToBeSaved.setUser(savedUser);
        taskRepository.save(taskToBeSaved);

        int bit = taskToBeSaved.getDone() ? 1 : 0;

        List<Task> taskList = taskRepository.findByUserAndDone(taskToBeSaved.getUser().getId(), bit);

        Assertions.assertThat(taskList)
                .isNotNull()
                .isNotEmpty()
                .hasSize(1);
        Assertions.assertThat(taskList.get(0).getUser()).isEqualTo(taskToBeSaved.getUser());
        Assertions.assertThat(taskList.get(0).getDone()).isEqualTo(taskToBeSaved.getDone());
    }

}