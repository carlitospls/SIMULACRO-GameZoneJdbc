package es.daw.gamezonejdbc.service;

import es.daw.gamezonejdbc.exception.JuegoDuplicadoException;
import es.daw.gamezonejdbc.exception.RecursoNoEncontradoException;
import es.daw.gamezonejdbc.model.Plataforma;
import es.daw.gamezonejdbc.repository.PlataformaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlataformaService {

    private final PlataformaRepository plataformaRepository;


    public List<Plataforma> listar() {
        return plataformaRepository.findAll();
    }

    public Plataforma crear(Plataforma plataforma) {

        if (plataformaRepository.existsByNombreIgnoreCase(plataforma.getNombre())){
            throw new RecursoNoEncontradoException(plataforma.getNombre());
        }

        plataformaRepository.save(plataforma);
        return plataforma;
    }

    public  Plataforma actualizar(Plataforma plataforma) {

        if (plataformaRepository.existsByNombreIgnoreCaseAndCodigoNot(plataforma.getNombre(),plataforma.getCodigo())){
            throw new JuegoDuplicadoException(plataforma.getNombre());
        }

        plataformaRepository.update(plataforma);

        return plataforma;
    }

}
