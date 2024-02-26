/*
package com.example.miprimeraapirest.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller

public class DriverDisplayController {
    @Value("${texto.index")

    // PROBAR EN CLASE SI FUNCIONA

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("hora", LocalDateTime.now());
        model.addAttribute("titulo","Programa de prueba");


        return "index";
    }

    @RequestMapping(value = "/listar")
    public String listar(Model model){
        model.addAttribute("titulo","Listado de usuarios");
        return "listar";
    }




}
*/
// ESTO CON DRIVERS
    /*
    @ModelAttribute("usuarios")
    public List<Usuario> poblarUsuarios(){
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Usuario("Pepe","Pepito","pepe@email.com"));
        usuarios.add(new Usuario("Juan","Juanito","juan@email.com"));
        usuarios.add(new Usuario("Luis","Luisito","pepe@email.com"));
        return usuarios;
    }
    */
