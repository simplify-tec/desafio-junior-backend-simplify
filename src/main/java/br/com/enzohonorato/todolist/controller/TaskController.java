package br.com.enzohonorato.todolist.controller;

import br.com.enzohonorato.todolist.domain.Task;
import br.com.enzohonorato.todolist.requests.task.TaskGetResponseBody;
import br.com.enzohonorato.todolist.requests.task.TaskPostRequestBody;
import br.com.enzohonorato.todolist.requests.task.TaskPriority;
import br.com.enzohonorato.todolist.requests.task.TaskPutRequestBody;
import br.com.enzohonorato.todolist.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasks")
@RequiredArgsConstructor
public class TaskController {
    private final TaskService taskService;

    @GetMapping(path = "/list")
    public ResponseEntity<List<TaskGetResponseBody>> findByUser() {
        return new ResponseEntity<>(taskService.findByUser(), HttpStatus.OK);
    }

    @GetMapping(path = "/list/priority")
    public ResponseEntity<List<TaskGetResponseBody>> findByPriority(@RequestParam(name = "priority") TaskPriority taskPriority) {

        return new ResponseEntity<>(taskService.findByUserAndPriority(taskPriority), HttpStatus.OK);
    }

    @GetMapping(path = "/list/done")
    public ResponseEntity<List<TaskGetResponseBody>> findByDone(@RequestParam(name = "done") Boolean done) {

        return new ResponseEntity<>(taskService.findByUserAndDone(done), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Task> save(@RequestBody TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TaskPutRequestBody taskPutRequestBody) {
        taskService.update(taskPutRequestBody);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
