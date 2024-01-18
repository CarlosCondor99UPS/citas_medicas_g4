package ups.edu.ec.sistemacitasmedicas.Exceptions;

public class PersonaExistenteException extends RuntimeException {
    public PersonaExistenteException(String message) {
        super(message);
    }
}