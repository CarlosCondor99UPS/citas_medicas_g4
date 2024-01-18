package ups.edu.ec.sistemacitasmedicas.Exceptions;

public class PersonaNoEncontradaException extends RuntimeException {
    public PersonaNoEncontradaException(String mensaje) {
        super(mensaje);
    }
}