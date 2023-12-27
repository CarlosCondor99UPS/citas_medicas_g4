package com.recursos.recursos.controller;

import com.recursos.recursos.entity.Persona;
import com.recursos.recursos.entity.Usuario;
import com.recursos.recursos.services.PersonaServicio;
import com.recursos.recursos.services.UsuarioServicio;


import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


@WebMvcTest(UsuarioControlador.class)
class UsuarioControladorTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioServicio usuarioServicio;

    @MockBean
    private PersonaServicio personaServicio;


    @Test
    public void testGuardarUsuario() throws Exception {
        // Datos de prueba
        Usuario usuario = new Usuario();
        usuario.setUsuario_id(5);
        usuario.setEncargo("General");
        usuario.setUsername("sss");
        usuario.setPassword("234");
        usuario.setEstado(true);

        Persona persona = new Persona();
        persona.setPersona_id(7);
        // Asigna la persona al usuario
        usuario.setPersona(persona);

        // Simula el comportamiento del servicio de persona
        given(personaServicio.get(7)).willReturn(Optional.of(persona));

        // Simula el comportamiento del servicio de usuario
        given(usuarioServicio.guardarUsuario(any(Usuario.class))).willReturn(usuario);

        // Realiza la solicitud POST con el cuerpo del usuario en formato JSON
        mockMvc.perform(post("/usuarios/registrar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"usuario_id\":5,\"encargo\":\"General\",\"username\":\"sss\",\"password\":\"234\",\"estado\":true,\"persona\":{\"persona_id\":7}}"))
                .andExpect(status().isOk());
    }

    @Test
    public void testBuscarUsuarioPorNombre() throws Exception {
        // Datos de prueba
        String nombreUsuario = "sss";

        Usuario usuario = new Usuario();
        usuario.setUsuario_id(5);
        usuario.setEncargo("General");
        usuario.setUsername(nombreUsuario);
        usuario.setPassword("234");
        usuario.setEstado(true);

        Persona persona = new Persona();
        persona.setPersona_id(7);
        usuario.setPersona(persona);

        // Simula el comportamiento del servicio de usuario
        given(usuarioServicio.obtenerUsuario(nombreUsuario)).willReturn((usuario));

        // Realiza la solicitud GET para buscar el usuario por su nombre
        ResultActions response = mockMvc.perform(MockMvcRequestBuilders
                .get("/usuarios/{username}", nombreUsuario)
                .contentType(MediaType.APPLICATION_JSON));

        response.andDo(MockMvcResultHandlers.print());

    }
    @Test
    public void testEliminarUsuarioPorId() throws Exception {
        // ID de la persona a eliminar
        int id = 1;

        // Simula la eliminación exitosa de la persona sin devolver ningún valor
        doNothing().when(usuarioServicio).eliminarUsuario(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/usuarios/{usuarioId}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

    @Test
    public void testIniciarSesion() throws Exception {
        // Datos de prueba
        String username = "sss";
        String password = "234";

        Usuario usuario = new Usuario();
        usuario.setUsuario_id(5);
        usuario.setEncargo("General");
        usuario.setUsername(username);
        usuario.setPassword(password);
        usuario.setEstado(true);

        Persona persona = new Persona();
        persona.setPersona_id(7);
        // Asigna la persona al usuario
        usuario.setPersona(persona);

        // Simula el comportamiento del servicio de usuario
        given(usuarioServicio.obtenerUsuario(username)).willReturn((usuario));

        // Realiza la solicitud POST con el cuerpo del usuario en formato JSON
        MvcResult result = mockMvc.perform(post("/usuarios/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"sss\",\"password\":\"234\"}"))
                .andExpect(status().isOk())
                .andReturn();
        // Verifica si se ha iniciado sesión correctamente
        String response = result.getResponse().getContentAsString();
        assertTrue(response.contains("Inicio de sesión exitoso"));
        System.out.println("Inicio de sesión exitoso");
    }

    @Test
    public void testActualizarUsuario() throws Exception {
        // Datos de prueba
        String username = "sss";
        Usuario usuario = new Usuario();
        usuario.setEncargo("General");
        usuario.setPassword("234");
        usuario.setEstado(true);
        usuario.setGeneral(true);
        usuario.setAdministrador(false);

        Persona persona = new Persona();
        persona.setPersona_id(7);
        // Asigna la persona al usuario
        usuario.setPersona(persona);

        // Simula el comportamiento del servicio de persona
        given(personaServicio.get(7)).willReturn(Optional.of(persona));

        // Simula el comportamiento del servicio de usuario
        given(usuarioServicio.obtenerUsuario(username)).willReturn(usuario);
        given(usuarioServicio.guardarUsuario(any(Usuario.class))).willReturn(usuario);

        System.out.println("Antes de actualizar el usuario:");
        System.out.println("Usuario existente: " + usuario);

        // Realiza la solicitud PUT con el cuerpo del usuario en formato JSON
        mockMvc.perform(put("/usuarios/{username}", username)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"encargo\":\"General\",\"password\":\"234\",\"estado\":true,\"general\":true,\"administrador\":false,\"persona\":{\"persona_id\":7}}"))
                .andExpect(status().isOk());

        System.out.println("Después de actualizar el usuario:");
        System.out.println("Usuario actualizado: " + usuario);
    }

    @Test
    public void testEliminarUsuario() throws Exception {

        int id = 3;

        // Simula la eliminación exitosa del usuario sin devolver ningún valor
        doNothing().when(usuarioServicio).eliminarUsuario(id);

        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/usuarios/{usuarioId}", id)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string(""));
    }

}