package com.vinidev.DesafioSimplifyTec.rest;

import com.vinidev.DesafioSimplifyTec.DAO.TarefaDAO;
import com.vinidev.DesafioSimplifyTec.DAO.UsuarioDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexRestController {
    private final TarefaDAO tarefaDAO;
    private final UsuarioDAO usuarioDAO;
    // Injetando os DAOs para os endpoints
    @Autowired
    public IndexRestController(TarefaDAO tarefaDAO, UsuarioDAO usuarioDAO) {
        this.tarefaDAO = tarefaDAO;
        this.usuarioDAO = usuarioDAO;
    }

    @RequestMapping("/")
    public String indexPage(Model model){
        return "index";
    }
    @GetMapping("/login")
    public String loginPage(Model model){
        return "login";
    }
    @RequestMapping("/cadastro")
    public String cadastroPage(Model model){
        return "cadastro";
    }
}
