package br.com.enzohonorato.todolist.controller;

import br.com.enzohonorato.todolist.requests.task.*;
import br.com.enzohonorato.todolist.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Listar todos as tarefas do usuário logado", tags = {"tasks - USER"})
    @GetMapping(path = "/list")
    public ResponseEntity<List<TaskGetResponseBody>> findByUser() {
        return new ResponseEntity<>(taskService.findByUser(), HttpStatus.OK);
    }

    @Operation(summary = "Listar as tarefas do usuário logado de acordo com o parâmetro 'priority' (LOW, MEDIUM, HIGH) passado", tags = {"tasks - USER"})
    @GetMapping(path = "/list/priority")
    public ResponseEntity<List<TaskGetResponseBody>> findByPriority(@RequestParam(name = "priority") TaskPriority taskPriority) {

        return new ResponseEntity<>(taskService.findByUserAndPriority(taskPriority), HttpStatus.OK);
    }

    @Operation(summary = "Listar as tarefas do usuário logado de acordo com o parâmetro 'done' (false, true) passado", tags = {"tasks - USER"})
    @GetMapping(path = "/list/done")
    public ResponseEntity<List<TaskGetResponseBody>> findByDone(@RequestParam(name = "done") Boolean done) {

        return new ResponseEntity<>(taskService.findByUserAndDone(done), HttpStatus.OK);
    }

    @Operation(summary = "Salvar uma tarefa para o usuário logado de acordo com o conteúdo do body da requisição", tags = {"tasks - USER"})
    @PostMapping
    public ResponseEntity<SavedTaskResponseBody> save(@RequestBody TaskPostRequestBody taskPostRequestBody) {
        return new ResponseEntity<>(taskService.save(taskPostRequestBody), HttpStatus.CREATED);
    }

    @Operation(summary = "Deletar uma tarefa do usuário logado de acordo com o 'id' de tarefa passado como parâmetro", tags = {"tasks - USER"})
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Atualizar uma tarefa do usuário logado de acordo com o conteúdo do body da requisição", tags = {"tasks - USER"})
    @PutMapping
    public ResponseEntity<Void> update(@RequestBody TaskPutRequestBody taskPutRequestBody) {
        taskService.update(taskPutRequestBody);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
