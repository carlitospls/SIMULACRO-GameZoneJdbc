package es.daw.gamezonejdbc.repository;

import es.daw.gamezonejdbc.model.Plataforma;

public interface PlataformaRepository extends GenericRepository<Plataforma, Integer> {

    boolean existsByNombreIgnoreCaseAndCodigoNot(String nombre, Integer codigo);

    boolean existsByNombreIgnoreCase(String nombre);
}
