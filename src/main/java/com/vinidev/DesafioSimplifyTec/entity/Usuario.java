package com.vinidev.DesafioSimplifyTec.entity;

import jakarta.persistence.*;
// Iniciando entidade para criacao/modificacao de usuarios
@Entity(name = "Usuario")
public class Usuario {
    // Primary key
    @Id
    // Aqui estamos setando para o id de usuario ser gerado utilizando a logica do auto_increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    // Atribuindo colunas com as devidas variaveis
    @Column(name = "id")
    private int id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email")
    private String email;
    @Column(name = "senha")
    private String senha;

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // Construtores

    public Usuario() {
    }

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }
    // Retornando info do objeto
    @Override
    public String toString() {
        return "Usuario{ id="+ getId() + '\'' +
                "Nome=" + getNome() + '\'' +
                "Email=" +getEmail() + '\'' +
                "Senha="+ getSenha();
    }
}
