package com.recursos.recursos.repositorio;

import com.recursos.recursos.entity.Usuario;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositorioTest {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;



    @Test
    void testGuardarEspecialidad(){

        Usuario usuario = new Usuario(10,"General","pruebita","pruebita",true);

        Usuario personaGuardado = usuarioRepositorio.save(usuario);

        Assertions.assertNotNull(personaGuardado);
    }
    @Test
    void testObtenerUsuarioPorNombre() {
        Usuario usuario = new Usuario(10,"General","pruebita","pruebita",true);
        usuarioRepositorio.save(usuario);

        Optional <Usuario> personaObtenidaOptional = usuarioRepositorio.findByUsername("pruebita");

        // Verificar que la persona obtenida no sea nula
        Assertions.assertNotNull(personaObtenidaOptional);

        // Verificar si el Optional contiene un valor antes de acceder a él
        if (personaObtenidaOptional.isPresent()) {
            Usuario personaObtenida = personaObtenidaOptional.get();

            // Verificar que la información de la persona obtenida coincida con la información de la persona de prueba
            Assertions.assertEquals(usuario.getEncargo(), personaObtenida.getEncargo());
            Assertions.assertEquals(usuario.getUsername(), personaObtenida.getUsername());
            Assertions.assertEquals(usuario.getPassword(), personaObtenida.getPassword());
            Assertions.assertEquals(usuario.isEstado(), personaObtenida.isEstado());



        } else {
            // Si el Optional está vacío, lanzar una excepción o realizar alguna otra acción según lo requieras

        }


    }

    @Test
    public void testListarUsuario(){
        List<Usuario> usuarios = usuarioRepositorio.findAll();
        assertThat(usuarios).size().isGreaterThan(0);
    }

    @Test
    public void eliminarUsuario(){

        Integer id = 2;
        boolean existe =  usuarioRepositorio.findById(id).isPresent();
        usuarioRepositorio.deleteById(id);
        boolean Noexiste =  usuarioRepositorio.findById(id).isPresent();

        assertTrue(existe);
        assertFalse(Noexiste);

    }

}