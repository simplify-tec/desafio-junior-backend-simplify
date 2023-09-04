package com.vinidev.DesafioSimplifyTec;

import com.vinidev.DesafioSimplifyTec.DAO.TarefaDAO;
import com.vinidev.DesafioSimplifyTec.DAO.UsuarioDAO;
import com.vinidev.DesafioSimplifyTec.common.MetodosTarefa;
import com.vinidev.DesafioSimplifyTec.common.MetodosUsuario;
import com.vinidev.DesafioSimplifyTec.entity.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
public class DesafioSimplifyTecApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesafioSimplifyTecApplication.class, args);
	}
	// Testes
	@Bean
	public CommandLineRunner commandLineRunner(TarefaDAO tarefaDAO, UsuarioDAO usuarioDAO) {
		return runner ->{
			System.out.println("Application is running");/*
			MetodosUsuario gerenciadorUsuarios = new MetodosUsuario();
			gerenciadorUsuarios.criarUsuario(usuarioDAO, "Carlos do teste", "carlos@teste.com", "123!Senha");
			Usuario usuarioTeste = usuarioDAO.findById(2);
			MetodosTarefa gerenciadorTarefas = new MetodosTarefa();
			gerenciadorTarefas.criarTarefa(tarefaDAO,"Criar sistema post","Sistema post para gerenciar tarefas",2,usuarioTeste);
			*/
		};
	}
}
