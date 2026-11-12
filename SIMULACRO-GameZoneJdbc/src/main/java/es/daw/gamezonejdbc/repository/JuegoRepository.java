package es.daw.gamezonejdbc.repository;

import es.daw.gamezonejdbc.model.Juego;

/** Repositorio de juegos: CRUD genérico + consultas propias. */
public interface JuegoRepository extends GenericRepository<Juego, Integer> {

    /** ¿Hay algún juego de esta plataforma? */
    boolean existsByPlataformaCodigo(Integer codigoPlataforma);
}
