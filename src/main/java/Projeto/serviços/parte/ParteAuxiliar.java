package Projeto.serviços.parte;

import Projeto.entidades.Parte;
import Projeto.repositorio.ParteRepositorio;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class ParteAuxiliar {

    private final ParteRepositorio processoRepository;

    public Parte returnValidParteById(Long id) {

        return processoRepository
                .findById(id)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parte não encontrada"));
    }

    public Parte returnValidParteSemProcessoById(Long id) {

        Parte parte =
                processoRepository
                        .findById(id)
                        .orElseThrow(
                                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Parte não encontrada"));

        if (parte.getProcesso() != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Parte já está em um processo");
        } else {
            return parte;
        }
    }
}
