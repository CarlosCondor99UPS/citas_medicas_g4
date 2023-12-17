package com.recursos.recursos.services;

import com.recursos.recursos.entity.Usuario;

public interface UsuarioServicio {
    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Integer id);

}
