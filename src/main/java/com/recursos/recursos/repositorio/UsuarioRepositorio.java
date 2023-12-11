package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepositorio extends JpaRepository<Usuario,Integer> {
    Optional<Usuario> findByUsername(String usuario);
}

