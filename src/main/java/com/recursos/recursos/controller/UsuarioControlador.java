package com.recursos.recursos.controller;

import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.entity.Usuario;
import com.recursos.recursos.repositorio.PersonaRepositorio;
import com.recursos.recursos.services.UsuarioServicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
@CrossOrigin("*")
public class UsuarioControlador {
    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    @PostMapping("/registrar")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception {

        // Obtener la Persona por su ID
        Persona persona = personaRepositorio.findById(usuario.getPersona().getPersona_id())
                .orElseThrow(() -> new Exception("Persona no encontrada"));

        // Asignar la Persona al Usuario
        usuario.setPersona(persona);

        // Guardar el Usuario en la base de datos
        return usuarioServicio.guardarUsuario(usuario);
    }

    @GetMapping("/{username}")
    public Usuario obtenerUsuario(@PathVariable("username") String username) {
        return usuarioServicio.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Integer usuarioId) {
        usuarioServicio.eliminarUsuario(usuarioId);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Usuario usuario) {
        // Obtén el usuario de la base de datos por el nombre de usuario
        Usuario usuarioBD = usuarioServicio.obtenerUsuario(usuario.getUsername());

        // Verifica si el usuario existe y si la contraseña coincide
        if (usuarioBD != null && usuarioBD.getPassword().equals(usuario.getPassword())) {
            return ResponseEntity.ok().body("{\"message\": \"Inicio de sesión exitoso\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Credenciales de inicio de sesión inválidas\"}");
        }
    }
    @PutMapping("/{username}")
    public Usuario actualizarUsuario(@PathVariable("username") String username, @RequestBody Usuario usuario) throws Exception {
        // Verificar si el usuario existe en la base de datos
        Usuario usuarioExistente = usuarioServicio.obtenerUsuario(username);
        if (usuarioExistente == null) {
            throw new Exception("Usuario no encontrado");
        }

        // Obtener la Persona por su ID
        Persona persona = personaRepositorio.findById(usuario.getPersona().getPersona_id())
                .orElseThrow(() -> new Exception("Persona no encontrada"));

        // Actualizar los datos del usuario existente
        usuarioExistente.setEncargo(usuario.getEncargo());
        usuarioExistente.setUsername(usuario.getUsername());
        usuarioExistente.setPassword(usuario.getPassword());
        usuarioExistente.setEstado(usuario.isEstado());
        usuarioExistente.setGeneral(usuario.isGeneral());
        usuarioExistente.setAdministrador(usuario.isAdministrador());
        usuarioExistente.setPersona(persona);

        // Guardar los cambios en la base de datos
        return usuarioServicio.guardarUsuario(usuarioExistente);
    }
}
