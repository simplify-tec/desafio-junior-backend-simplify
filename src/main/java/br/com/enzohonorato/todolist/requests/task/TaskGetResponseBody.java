package br.com.enzohonorato.todolist.requests.task;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TaskGetResponseBody {
    private Long id;
    private String name;
    private String description;
    private TaskPriority priority;
    private Boolean done;
}
