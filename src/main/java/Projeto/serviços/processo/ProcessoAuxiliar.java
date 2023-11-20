package Projeto.serviços.processo;

import Projeto.entidades.Processo;
import Projeto.repositorio.ProcessoRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ProcessoAuxiliar {

    private final ProcessoRepositorio processoRepositorio;

    public Processo returnValidProcessoById(Long id) {

        return processoRepositorio
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Processo não encontrado"));
    }
}
