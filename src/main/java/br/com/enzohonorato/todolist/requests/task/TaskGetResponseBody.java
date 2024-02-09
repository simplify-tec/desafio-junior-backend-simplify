package br.com.enzohonorato.todolist.requests.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskGetResponseBody {
    private Long id;
    private String name;
    private String description;
    private TaskPriority priority;
    private Boolean done;
}
