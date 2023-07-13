package com.todo.list.services;

import com.todo.list.entities.Todo;
import com.todo.list.repositories.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    @Autowired
    private TodoRepository todo;
    @Autowired
    private Notification notify;

    // Insere uma nova tarefa
    public ResponseEntity<?> insertTask(Todo task){
        if(task.getNome().equals("")){
            notify.setNotification("Informe o nome da tarefa !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        }else if(task.getDescricao().equals("")){
            notify.setNotification("Informe a Descrição !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        } else if(task.getPrioridade() < 1 || task.getPrioridade() > 4) {
            notify.setNotification("Informe o Nível de Prioridade válido entre 1 e 4 !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        }else{
            return new ResponseEntity<>(todo.save(task), HttpStatus.CREATED);
        }
    }

    public ResponseEntity<?> listTask(){
        return new ResponseEntity<>(todo.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> searchToId(int id){
        if(todo.countById(id) == 0){
            notify.setNotification("Não foi encontrada nenhuma tarefa listada !");
            return new ResponseEntity<>(notify, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo.findById(id), HttpStatus.OK);
    }

    public ResponseEntity<?> UpdateTodo(Todo obj){
        if(obj.getId() == 0){
            notify.setNotification("Id invalido !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        } else if(obj.getNome().equals("")){
            notify.setNotification("Não foi encontrada nenhuma tarefa listada !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        }else if(obj.getDescricao().equals("")){
            notify.setNotification("Informe uma descrição !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        }else if(obj.getPrioridade() < 1 || obj.getPrioridade() > 4){
            notify.setNotification("Informe o Nível de Prioridade válido entre 1 e 4 !");
            return new ResponseEntity<>(notify, HttpStatus.BAD_REQUEST);
        } else if (obj.isRealizado()) {
            notify.setNotification("Tarefa concluída com sucesso !");
            todo.delete(obj);
            return new ResponseEntity<>(notify, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(todo.save(obj), HttpStatus.OK);
        }
    }

    public ResponseEntity<?> deleteId(int id){
        notify.setNotification("Tarefa Não encontrada !");
        if(todo.countById(id) == 0){
            return new ResponseEntity<>(notify, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(todo.deleteById(id), HttpStatus.OK);
    }


}
