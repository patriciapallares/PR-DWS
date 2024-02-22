package com.example.pruebaspringvistas.controller;


import com.example.pruebaspringvistas.model.Usuario;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller

public class MiControlador {

    @Value("${texto.index")

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

    @RequestMapping(value = "/listar")
    public String listar(Model model){
        model.addAttribute("titulo","Listado de usuarios");
        return "listar";
    }

    @ModelAttribute("usuarios")
    public List<Usuario> poblarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Pepe","Pepito","pepe@email.com"));
        usuarios.add(new Usuario("Juan","Juanito","juan@email.com"));
        usuarios.add(new Usuario("Luis","Luisito","pepe@email.com"));
        return usuarios;
    }

}