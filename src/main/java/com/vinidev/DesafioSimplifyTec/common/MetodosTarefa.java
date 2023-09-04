package com.vinidev.DesafioSimplifyTec.common;

import com.vinidev.DesafioSimplifyTec.DAO.TarefaDAO;
import com.vinidev.DesafioSimplifyTec.entity.Tarefa;
import com.vinidev.DesafioSimplifyTec.entity.Usuario;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

// Nessa classe implantaremos os metodos da API para as tarefas
public class MetodosTarefa {
    // Lembre-se de criar uma instancia da TarefaDAO para podermos usar os metodos do Hibernate
    // Necessario uma instancia usuario para criar tarefa
    public void criarTarefa(TarefaDAO tarefaDAO, String nome, String descricao, int prioridade, Usuario usuario){
        System.out.println("Criando nova tarefa...");
        // Formatando a data que iremos recuperar
        DateTimeFormatter formatoDeData = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        // Recuperando data do sistema
        LocalDateTime dataAtual = LocalDateTime.now();
        // Formatando data
        String dataFormatadaStr = dataAtual.format(formatoDeData);
        // Convertendo data formatada
        LocalDateTime dataFormatada = LocalDateTime.parse(dataFormatadaStr, formatoDeData);
        // Coletando id do usuario que criou a tarefa
        int idCriador = usuario.getId();
        // Criando tarefa
        Tarefa novaTarefa = new Tarefa(nome,descricao,0,prioridade,dataFormatada,idCriador);
        System.out.println("Nova tarefa criada: \n"+ novaTarefa.toString());
        // Salvando tarefa no banco
        tarefaDAO.save(novaTarefa);
        System.out.println("tarefa salva");
    }
    // Metodo para o endpoint
    public void atualizarTarefa(TarefaDAO tarefaDAO, Tarefa tarefaAtual, String nome, String descricao, int realizado, int prioridade){
        System.out.println("Atualizando tarefa atual...");
        tarefaAtual.setNome(nome);
        tarefaAtual.setDescricao(descricao);
        tarefaAtual.setRealizado(realizado);
        tarefaAtual.setPrioridade(prioridade);
        tarefaDAO.update(tarefaAtual);
    }
    // Metodo para o menu/botao
    public void atualizarTarefaRealizado(TarefaDAO tarefaDAO, Tarefa tarefaAtual, int realizado){
        tarefaAtual.setRealizado(realizado);
        tarefaDAO.update(tarefaAtual);
    }
    // Metodo para o menu/botao
    public void atualizarTarefaPrioridade(TarefaDAO tarefaDAO, Tarefa tarefaAtual, int prioridade){
        tarefaAtual.setPrioridade(prioridade);
        tarefaDAO.update(tarefaAtual);
    }
    // Deletando tarefa
    public void deletarTarefa(TarefaDAO tarefaDAO, Tarefa tarefaAtual){
        System.out.println("Excluindo tarefa com o id: "+ tarefaAtual.getId());
        tarefaDAO.delete(tarefaAtual);
    }
}
