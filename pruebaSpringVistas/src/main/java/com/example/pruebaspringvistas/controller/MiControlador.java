package com.example.pruebaspringvistas.controller;


import com.example.pruebaspringvistas.model.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller

public class MiControlador {
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hora", LocalDateTime.now());
        model.addAttribute("titulo","Programa de prueba");

        /*
        Se puede hacer también con Map de Java (diapo 8/20)
        * /
         */
        return "index";
    }

    @RequestMapping("/perfil")
    public String perfil(Model model){
        Usuario usuario = new Usuario("Fran","García","email@email.com");
        model.addAttribute("titulo","Página de perfil");
        model.addAttribute("usuario", usuario);
        return "perfil";
    }
}