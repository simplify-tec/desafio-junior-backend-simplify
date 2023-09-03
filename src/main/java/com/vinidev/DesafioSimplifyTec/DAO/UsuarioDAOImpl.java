package com.vinidev.DesafioSimplifyTec.DAO;

import com.vinidev.DesafioSimplifyTec.entity.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UsuarioDAOImpl implements UsuarioDAO{
    // O Entity Manager nesse caso, e utilizado para realizar as operacoes com o banco de dados
    EntityManager gerenciadorDeEntidade;
    // Injetando o Entity manager
    @Autowired
    public UsuarioDAOImpl(EntityManager theEntityManager){
        gerenciadorDeEntidade = theEntityManager;
    }
    // Salvando usuario
    @Override
    @Transactional
    public void save(Usuario usuarioAtual){
        gerenciadorDeEntidade.persist(usuarioAtual);
    }
    // Encontrando por id (primary key)
    @Override
    public Usuario findById(int id){
        return gerenciadorDeEntidade.find(Usuario.class, id);
    }
    // Retorna todos
    @Override
    public List<Usuario> findAll(){
        return gerenciadorDeEntidade.createQuery("SELECT p from Usuario p",Usuario. class).getResultList();
    }
    // Retorna todos os usuarios com x nome
    public List<Usuario> findByName(String nome){
        TypedQuery<Usuario> theQuery = gerenciadorDeEntidade.createQuery("SELECT p from Usuario p where nome=:nome",Usuario.class);
        theQuery.setParameter("nome",nome);
        return theQuery.getResultList();
    }
    // Atualiza o usuario
    @Override
    @Transactional
    public void update(Usuario usuarioAtual) {
        gerenciadorDeEntidade.merge(usuarioAtual);
    }
    // Exclui o usuario
    @Override
    @Transactional
    public void delete(Usuario usuarioDeletado){
        gerenciadorDeEntidade.remove(usuarioDeletado);
    }
}
