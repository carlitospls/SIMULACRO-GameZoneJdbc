package es.daw.gamezonejdbc.service;

import es.daw.gamezonejdbc.exception.RecursoNoEncontradoException;
import es.daw.gamezonejdbc.model.Plataforma;
import es.daw.gamezonejdbc.model.Juego;
import es.daw.gamezonejdbc.repository.PlataformaRepository;
import es.daw.gamezonejdbc.repository.JuegoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// Capa de lógica: reglas de negocio. No sabe nada de HTTP ni de SQL.
@Service
@RequiredArgsConstructor
public class JuegoService {

    private final JuegoRepository juegoRepository;
    private final PlataformaRepository plataformaRepository;

    public List<Juego> listar() {
        return juegoRepository.findAll();
    }

    public Juego buscarPorCodigo(Integer codigo) {
        return juegoRepository.findById(codigo)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe el juego con código " + codigo));
    }

    public Juego crear(Juego juego) {
        prepararParaGuardar(juego);
        juegoRepository.save(juego);
        return juego;
    }

    public Juego actualizar(Juego juego) {
        buscarPorCodigo(juego.getCodigo());
        prepararParaGuardar(juego);
        juegoRepository.update(juego);
        return juego;
    }

    public void borrar(Integer codigo) {
        buscarPorCodigo(codigo);
        juegoRepository.delete(codigo);
    }

    // Reglas comunes a crear y actualizar
    private void prepararParaGuardar(Juego juego) {
        juego.setNombre(juego.getNombre().trim());

        Integer codigoPlataforma = juego.getPlataforma().getCodigo();
        Plataforma plataforma = plataformaRepository.findById(codigoPlataforma)
                .orElseThrow(() -> new RecursoNoEncontradoException(
                        "No existe la plataforma con código " + codigoPlataforma));
        juego.setPlataforma(plataforma);
    }
}
