package br.com.jonathnawill.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.jonathnawill.entity.Todo;
import br.com.jonathnawill.repository.todoRepository;

@Service
public class todoService {

	@Autowired
	private todoRepository repository;

	public List<Todo> create(Todo todo) {
		repository.save(todo);

		return list();
	}

	public List<Todo> list() {
		Sort sort = Sort.by(Direction.DESC, "prioridade").and(Sort.by(Direction.ASC, "id"));

		return repository.findAll(sort);
	}

	public List<Todo> update(Long id, Todo todo) {
		repository.findById(id);
		repository.save(todo);
		return list();
	}

	public List<Todo> delete(Long id) {
		repository.deleteById(id);
		return list();
	}

}
