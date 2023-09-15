package br.com.jonathnawill.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jonathnawill.entity.Todo;
import br.com.jonathnawill.service.todoService;

@RestController
@RequestMapping(value = "/todos")
public class todoResources {

	@Autowired
	private todoService service;

	@PostMapping
	public List<Todo> create(@RequestBody Todo todo) {
		return service.create(todo);
	}

	@GetMapping
	public List<Todo> list() {
		return service.list();
	}

	@PutMapping
	public List<Todo> update(@RequestBody Todo todo) {
		return service.update(todo);
	}

	@DeleteMapping("/{id}")
	public List<Todo> delete(@PathVariable("id") Long id) {
		return service.delete(id);
	}
}
