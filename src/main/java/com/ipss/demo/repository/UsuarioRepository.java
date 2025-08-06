// src/main/java/com/ipss/demo/repository/UsuarioRepository.java
package com.ipss.demo.repository;

import com.ipss.demo.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByNombreAndContrasena(String nombre, String contrasena);

    Optional<Usuario> findByNombre(String nombre);
    
}