package com.todo.list.repositories;

import com.todo.list.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long> {

    List<Todo> findAll();
    @Query(value = "")
    int countById(int id);
    Todo findById(int id);
    void delete(Todo obj);
    Todo deleteById(int id);

}
