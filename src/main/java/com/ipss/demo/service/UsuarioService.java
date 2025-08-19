// src/main/java/com/ipss/demo/service/UsuarioService.java
package com.ipss.demo.service;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Usamos @Autowired para inyectar el repositorio
    // En lugar de inicializarlo en el constructor, dejamos que Spring lo inyecte
    // y lo usamos en otros métodos.

    // El código de inicialización de usuarios de prueba DEBE ir en otro lugar
    // o en un método anotado con @PostConstruct para que se ejecute después de la
    // inyección de dependencias.
    // Vamos a moverlo para que funcione correctamente.

    // Método para crear usuarios de prueba si no existen
    // Este método se ejecutará automáticamente después de que se cree el bean del
    // servicio
    // y el repositorio esté listo para ser usado.
    public void inicializarUsuarios() {
        if (usuarioRepository.findByNombre("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setNombre("admin");
            admin.setContrasena("admin");
            admin.setRol("Administrador");
            usuarioRepository.save(admin);
        }
        if (usuarioRepository.findByNombre("comensal").isEmpty()) {
            Usuario comensal = new Usuario();
            comensal.setNombre("comensal");
            comensal.setContrasena("pass123");
            comensal.setRol("Comensal");
            usuarioRepository.save(comensal);
        }
    }

    // Lo anterior es un ejemplo, pero una mejor práctica es crear un `DataLoader`
    // En tu caso, vamos a modificar el código para que funcione de inmediato.
    // **Por favor, usa este código final para tu UsuarioService:**

    // ==========================================================

    public Optional<Usuario> registrarUsuario(Usuario nuevoUsuario) {
        if (usuarioRepository.findByNombre(nuevoUsuario.getNombre()).isPresent()) {
            return Optional.empty(); // Falla si el usuario ya existe
        }
        nuevoUsuario.setRol("Comensal");
        return Optional.of(usuarioRepository.save(nuevoUsuario));
    }

    public Optional<Usuario> buscarUsuarioPorNombreYContrasena(String nombre, String contrasena) {
        return usuarioRepository.findByNombreAndContrasena(nombre, contrasena);
    }

    // Nuevo método para listar todos los usuarios
    public List<Usuario> obtenerTodosLosUsuarios() {
        return usuarioRepository.findAll();
    }

    // Nuevo método para actualizar a un usuario
    public Usuario actualizarUsuario(Long id, String nombre, String contrasena, String rol) {
        Optional<Usuario> usuarioExistente = usuarioRepository.findById(id);
        if (usuarioExistente.isPresent()) {
            Usuario existe = usuarioExistente.get();
            existe.setNombre(nombre);
            existe.setContrasena(contrasena);
            existe.setRol(rol);
            usuarioRepository.save(existe);
        }
        return null;
    }

}