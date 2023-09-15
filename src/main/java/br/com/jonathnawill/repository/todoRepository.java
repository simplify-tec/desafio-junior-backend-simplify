package br.com.jonathnawill.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.jonathnawill.entity.Todo;

@Repository
public interface todoRepository extends JpaRepository<Todo, Long> {

}
