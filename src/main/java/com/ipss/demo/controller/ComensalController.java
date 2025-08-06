// src/main/java/com/ipss/demo/controller/ComensalController.java
package com.ipss.demo.controller;

import com.ipss.demo.model.Usuario;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ComensalController {

    @GetMapping("/comensal/home")
    public String homeComensal(HttpSession session, Model model) {
        Usuario usuario = (Usuario) session.getAttribute("usuario");
        if (usuario == null || !"Comensal".equals(usuario.getRol())) {
            return "redirect:/login";
        }
        model.addAttribute("usuario", usuario);
        return "comensal/home";
    }
}