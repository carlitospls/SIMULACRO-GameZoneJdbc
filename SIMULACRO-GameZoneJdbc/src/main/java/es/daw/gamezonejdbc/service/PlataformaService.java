package es.daw.gamezonejdbc.service;

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
}
