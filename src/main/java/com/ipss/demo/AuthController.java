package com.ipss.demo;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class AuthController {
    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("usuario") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuario");
            if ("Administrador".equals(usuario.getRol())) {
                return "redirect:/admin/home"; // Redirige al mantenedor de mesas
            } else {
                return "redirect:/comensal/home"; // Redirige a la página principal del comensal
            }
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String mostrarLogin() {
        return "login"; // Devuelve la plantilla login.html
    }

    @PostMapping("/login")
    public String procesarLogin(@RequestParam String nombre, @RequestParam String contrasena, HttpSession session,
            Model model) {
        Optional<Usuario> usuarioOpt = usuarioService.buscarUsuarioPorNombreYContrasena(nombre, contrasena);

        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            session.setAttribute("usuario", usuario); // Guarda el usuario en la sesión
            if ("Administrador".equals(usuario.getRol())) {
                return "redirect:/admin/home";
            } else {
                return "redirect:/comensal/home";
            }
        } else {
            model.addAttribute("error", "Usuario o contraseña incorrectos");
            return "login"; // Vuelve al login con un mensaje de error
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // Invalida la sesión actual
        return "redirect:/login"; // Redirige al login
    }

    @GetMapping("/registrar")
    public String mostrarRegistro(Model model) {
        model.addAttribute("usuario", new Usuario());
        return "registrar"; // Devuelve la plantilla registrar.html
    }

    @PostMapping("/registrar")
    public String procesarRegistro(@ModelAttribute Usuario usuario, Model model, HttpSession session) {
        Optional<Usuario> nuevoUsuario = usuarioService.registrarUsuario(usuario);
        if (nuevoUsuario.isPresent()) {
            session.setAttribute("usuario", nuevoUsuario.get()); // Inicia sesión automáticamente
            return "redirect:/comensal/home";
        } else {
            model.addAttribute("error", "El nombre de usuario ya existe");
            return "registrar";
        }
    }
}
