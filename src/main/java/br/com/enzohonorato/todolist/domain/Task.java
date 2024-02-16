package br.com.enzohonorato.todolist.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tasks")
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tasks_id")
    private Long id;
    @Column(name = "tasks_name", nullable = false)
    private String name;
    @Column(name = "tasks_description", nullable = false)
    private String description;
    @Column(name = "tasks_priority", nullable = false)
    private String priority;
    @Column(name = "tasks_done", nullable = false)
    private Boolean done;
    @ManyToOne
    @JoinColumn(name = "fk_users_id", nullable = false)
    private User user;
}
