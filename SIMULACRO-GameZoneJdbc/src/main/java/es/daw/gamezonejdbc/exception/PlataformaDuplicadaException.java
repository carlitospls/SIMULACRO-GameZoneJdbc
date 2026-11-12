package es.daw.gamezonejdbc.exception;

public class PlataformaDuplicadaException extends RuntimeException {

    public PlataformaDuplicadaException(String nombre) {

        super("Ya existe esta plataforma");
    }
}
