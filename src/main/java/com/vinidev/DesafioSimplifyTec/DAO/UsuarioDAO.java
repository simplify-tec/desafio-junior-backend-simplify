package com.vinidev.DesafioSimplifyTec.DAO;

import com.vinidev.DesafioSimplifyTec.entity.Usuario;

import java.util.List;
// Aqui e onde vamos implementar os metodos de CRUD do usuario
public interface UsuarioDAO {
    // Salvar e commit do usuario
    void save(Usuario usuarioAtual);
    // Encontrar via id
    Usuario findById(int id);
    // Retorna todos os usuarios
    List<Usuario> findAll();

    List<Usuario> findByName(String name);
    void update(Usuario usuarioAtual);
    void delete(Usuario usuarioDeletado);
}
