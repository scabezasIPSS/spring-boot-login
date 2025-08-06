// src/main/java/com/ipss/demo/DataLoader.java
package com.ipss.demo;

import com.ipss.demo.model.Usuario;
import com.ipss.demo.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final UsuarioRepository usuarioRepository;

    public DataLoader(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void run(String... args) throws Exception {
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
}