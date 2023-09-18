package br.com.jonathnawill;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;

import br.com.jonathnawill.entity.Todo;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class TodolistDesafioBackendjrApplicationTests {
	@Autowired
	private WebTestClient webTestClient;

	@Test
	void testCreateTodoSuccess() {
		var todo = new Todo("todo 1", "desc todo 1", false, 1);

		webTestClient.post().uri("/todos").bodyValue(todo).exchange().expectStatus().isCreated().expectBody()
				.jsonPath("$").isArray().jsonPath("$[0].nome").isEqualTo(todo.getNome()).jsonPath("$[0].descricao")
				.isEqualTo(todo.getDescricao()).jsonPath("$[0].realizado").isEqualTo(todo.isRealizado())
				.jsonPath("$[0].prioridade").isEqualTo(todo.getPrioridade());
	}

	@Test
	public void testCreateTodoFailure() {
		webTestClient.post().uri("/todos").bodyValue(new Todo("", "", false, 0)).exchange().expectStatus()
				.isBadRequest();
	}

}