package ups.edu.ec.sistemacitasmedicas.servicio.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ups.edu.ec.sistemacitasmedicas.modelo.Persona;
import ups.edu.ec.sistemacitasmedicas.modelo.Usuario;
import ups.edu.ec.sistemacitasmedicas.repositorio.PersonaRepositorio;
import ups.edu.ec.sistemacitasmedicas.repositorio.UsuarioRepositorio;
import ups.edu.ec.sistemacitasmedicas.servicio.UsuarioServicio;

import java.util.Optional;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private PersonaRepositorio personaRepositorio;

    public Usuario guardarUsuario(Usuario usuario) throws Exception {
        // Obtener la Persona por su ID
        Persona persona = personaRepositorio.findById(usuario.getPersona().getPersona_id())
                .orElseThrow(() -> new Exception("Persona no encontrada"));

        // Asignar la Persona al Usuario
        usuario.setPersona(persona);
        usuario.asignarRolesPorEncargo();

        // Guardar el Usuario en la base de datos
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario obtenerUsuario(String username) {
        Optional<Usuario> usuario = usuarioRepositorio.findByUsername(username);
        return usuario.orElse(null);
    }

    @Override
    public void eliminarUsuario(Integer id) {
        usuarioRepositorio.deleteById(id);
    }

    public Usuario actualizarUsuario(Usuario usuario) throws Exception {
        // Verificar si el usuario existe en la base de datos
        Optional<Usuario> usuarioExistente = usuarioRepositorio.findByUsername(usuario.getUsername());
        if (!usuarioExistente.isPresent()) {
            throw new Exception("Usuario no encontrado");
        }

        // Obtener la Persona por su ID
        Persona persona = personaRepositorio.findById(usuario.getPersona().getPersona_id())
                .orElseThrow(() -> new Exception("Persona no encontrada"));

        // Actualizar los datos del usuario existente
        Usuario usuarioActualizado = usuarioExistente.get();
        usuarioActualizado.setEncargo(usuario.getEncargo());
        usuarioActualizado.setUsername(usuario.getUsername());
        usuarioActualizado.setPassword(usuario.getPassword());
        usuarioActualizado.setEstado(usuario.isEstado());
        usuarioActualizado.setGeneral(usuario.isGeneral());
        usuarioActualizado.setAdministrador(usuario.isAdministrador());
        usuarioActualizado.setPersona(persona);

        // Guardar los cambios en la base de datos
        return usuarioRepositorio.save(usuarioActualizado);
    }

}