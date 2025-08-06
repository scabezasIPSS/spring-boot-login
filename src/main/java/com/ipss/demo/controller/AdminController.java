// src/main/java/com/ipss/demo/controller/AdminController.java
package com.ipss.demo.controller;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/admin/home")
    public String homeAdmin(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "admin/home";
    }

    @GetMapping("/admin/usuarios")
    public String listarUsuarios(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Administrador".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuarios", usuarioService.obtenerTodosLosUsuarios());
        return "admin/lista_usuarios";
    }
}