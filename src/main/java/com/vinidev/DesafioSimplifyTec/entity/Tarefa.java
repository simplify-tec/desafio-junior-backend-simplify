package com.vinidev.DesafioSimplifyTec.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
// Iniciando entidade para criacao/modificacao de Tarefas
@Entity(name = "Tarefa")
public class Tarefa {
    // Primary key
    @Id
    // Aqui estamos setando para o id de usuario ser gerado utilizando a logica do auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    // Demais colunas
    @Column(name = "nome")
    private String nome;
    @Column(name = "descricao")
    private String descricao;
    @Column(name = "realizado")
    private int realizado;
    @Column(name="prioridade")
    private int prioridade;
    @Column(name = "data_criado")
    private LocalDateTime dataCriado;
    @Column(name = "id_criador")
    private int idCriador;

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getRealizado() {
        return realizado;
    }

    public void setRealizado(int realizado) {
        this.realizado = realizado;
    }

    public int getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(int prioridade) {
        this.prioridade = prioridade;
    }

    public LocalDateTime getDataCriado() {
        return dataCriado;
    }

    public void setDataCriado(LocalDateTime dataCriado) {
        this.dataCriado = dataCriado;
    }

    public int getIdCriador() {
        return idCriador;
    }

    public void setIdCriador(int idCriador) {
        this.idCriador = idCriador;
    }

    // Construtores

    public Tarefa() {
    }

    public Tarefa(String nome, String descricao, int realizado, int prioridade, LocalDateTime dataCriado, int idCriador) {
        this.nome = nome;
        this.descricao = descricao;
        this.realizado = realizado;
        this.prioridade = prioridade;
        this.dataCriado = dataCriado;
        this.idCriador = idCriador;
    }

    // Retorna informacoes da tarefa

    @Override
    public String toString() {
        return "Tarefa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", realizado=" + realizado +
                ", prioridade=" + prioridade +
                ", dataCriado=" + dataCriado +
                ", idCriador=" + idCriador +
                '}';
    }
}
