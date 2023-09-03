package com.vinidev.DesafioSimplifyTec.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IndexRestController {
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
