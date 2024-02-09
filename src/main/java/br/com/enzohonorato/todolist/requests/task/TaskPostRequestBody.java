package br.com.enzohonorato.todolist.requests.task;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskPostRequestBody {
    private String name;
    private String description;
    private TaskPriority priority;
    private Boolean done;
}
