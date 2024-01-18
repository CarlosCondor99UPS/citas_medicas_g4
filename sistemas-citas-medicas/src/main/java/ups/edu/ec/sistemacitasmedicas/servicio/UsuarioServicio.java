package ups.edu.ec.sistemacitasmedicas.servicio;


import ups.edu.ec.sistemacitasmedicas.modelo.Usuario;

public interface UsuarioServicio {

    public Usuario guardarUsuario(Usuario usuario) throws Exception;

    public Usuario obtenerUsuario(String username);

    public void eliminarUsuario(Integer id);

}
