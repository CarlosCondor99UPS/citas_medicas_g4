package ups.edu.ec.sistemacitasmedicas.Exceptions;

public class ServicioNoEncontradoException extends RuntimeException {

    public ServicioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}
