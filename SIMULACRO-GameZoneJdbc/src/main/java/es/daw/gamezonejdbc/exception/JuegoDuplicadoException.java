package es.daw.gamezonejdbc.exception;

public class JuegoDuplicadoException extends RuntimeException {

    public JuegoDuplicadoException(String nombre) {

        super("Ya existe ese juego en esa plataforma");
    }
}
