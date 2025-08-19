package com.ipss.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PutMapping("/{id}")
    public Usuario actualizarUsuario(@PathVariable Long id, @RequestParam String nombre,
            @RequestParam String contrasena, @RequestParam String rol) {
        return usuarioService.actualizarUsuario(id, nombre, contrasena, rol);
    }

}
