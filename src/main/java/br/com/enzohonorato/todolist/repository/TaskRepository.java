package br.com.enzohonorato.todolist.repository;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    List<Task> findByUser(User user);

    @Query(value = "select * from tasks where tasks_id = :tasks_id and fk_users_id = :fk_users_id limit 1", nativeQuery = true)
    Task findByIdAndUser(@Param("tasks_id") Long taskId,
                         @Param("fk_users_id") Long userId);

    @Query(value = "select * from tasks where fk_users_id = :fk_users_id and tasks_priority = :tasks_priority", nativeQuery = true)
    List<Task> findByUserAndPriority(@Param("fk_users_id") Long userId,
                               @Param("tasks_priority") String priority);

    @Query(value = "select * from tasks where fk_users_id = :fk_users_id and tasks_done = :tasks_done", nativeQuery = true)
    List<Task> findByUserAndDone(@Param("fk_users_id") Long userId,
                                     @Param("tasks_done") int bit);
}
