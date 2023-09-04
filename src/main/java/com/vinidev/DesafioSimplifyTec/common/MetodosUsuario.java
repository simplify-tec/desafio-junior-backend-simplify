package com.vinidev.DesafioSimplifyTec.common;

import com.vinidev.DesafioSimplifyTec.DAO.UsuarioDAO;
import com.vinidev.DesafioSimplifyTec.entity.Usuario;

public class MetodosUsuario {
    public void criarUsuario(UsuarioDAO usuarioDAO, String nome, String email, String senha){
        System.out.println("Criando novo usuario");
        // Instanciando novo usuario
        Usuario usuarioNovo = new Usuario(nome, email, senha);
        // Salvando usuario no banco
        usuarioDAO.save(usuarioNovo);
    }
    // Metodo para atualizar usuario no endpoint
    public void atualizarUsuario(UsuarioDAO usuarioDAO, Usuario usuarioAtual, String nome, String email, String senha){
        System.out.println("Atualizando usuario: "+usuarioAtual.toString());
        usuarioAtual.setNome(nome);
        usuarioAtual.setEmail(email);
        usuarioAtual.setSenha(senha);
        usuarioDAO.update(usuarioAtual);
    }
    // Deletando usuario :(
    public void deletarUsuario(UsuarioDAO usuarioDAO, Usuario usuarioAtual){
        System.out.println("Deletando usuario: "+usuarioAtual.toString());
        usuarioDAO.delete(usuarioAtual);
    }
}
