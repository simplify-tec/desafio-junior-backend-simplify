package com.todo.list.controllers;

import com.todo.list.entities.Todo;
import com.todo.list.services.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class TodoController {

    @Autowired
    private TodoService service;

    @PostMapping("/todo")
    public ResponseEntity<?> newTodo(@RequestBody Todo obj){
        return service.insertTask(obj);
    }

    @GetMapping("/todo")
    public ResponseEntity<?> listTodo(){
        return service.listTask();
    }

    @GetMapping("/todo/{id}")
    public ResponseEntity<?> findTodo(@PathVariable int id){
        return service.searchToId(id);
    }

    @PutMapping("/todo")
    public ResponseEntity<?> updateTodo(@RequestBody Todo obj){
        return service.UpdateTodo(obj);
    }

    @DeleteMapping("/todo/{id}")
    public ResponseEntity<?> removerTarefa(@PathVariable int id){
        return service.deleteId(id);
    }

}
