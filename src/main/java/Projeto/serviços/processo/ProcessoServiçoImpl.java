package Projeto.serviços.processo;

import Projeto.controles.processo.ProcessoRequisiçao;
import Projeto.controles.processo.ProcessoResposta;
import Projeto.entidades.Parte;
import Projeto.entidades.Processo;
import Projeto.mapeamento.ProcessoMapeamento;
import Projeto.repositorio.ProcessoRepositorio;
import Projeto.serviços.parte.ParteAuxiliar;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcessoServiçoImpl implements ProcessoServiço {

    private final ProcessoRepositorio processoRepositorio;

    private final ProcessoAuxiliar processoAuxiliar;

    private final ParteAuxiliar parteAuxiliar;

    @Transactional
    public Set<ProcessoResposta> list() {

        return processoRepositorio.findAll().stream()
                .map((Processo processo) -> ProcessoMapeamento.toResponse(processo, true))
                .collect(Collectors.toSet());
    }

    @Transactional
    public ProcessoResposta getById(Long id) {

        Processo processo = processoAuxiliar.returnValidProcessoById(id);
        return ProcessoMapeamento.toResponse(processo, true);
    }

    @Transactional
    public ProcessoResposta create(ProcessoRequisiçao request) {

        if (processoRepositorio.existsByNumero(request.getNumero())) {
            throw new DataIntegrityViolationException("Processo com esse número já existe");
        }

        Processo processo = ProcessoMapeamento.toEntity(request);

        processoRepositorio.save(processo);

        return ProcessoMapeamento.toResponse(processo, true);
    }

    @Transactional
    public ProcessoResposta update(Long id, ProcessoRequisiçao request) {

        Processo processo = processoAuxiliar.returnValidProcessoById(id);

        if (processoRepositorio.existsByNumero(request.getNumero())) {
            throw new DataIntegrityViolationException("Processo com esse número já existe");
        }

        ProcessoMapeamento.updateEntity(processo, request);

        processoRepositorio.save(processo);

        return ProcessoMapeamento.toResponse(processo, true);
    }

    @Transactional
    public ProcessoResposta delete(Long id) {

        Processo processo = processoAuxiliar.returnValidProcessoById(id);

        processoRepositorio.deleteById(id);

        return ProcessoMapeamento.toResponse(processo, true);
    }

    @Transactional
    public ProcessoResposta addPartes(Long id, Set<Long> parteIds) {

        Processo processo = processoAuxiliar.returnValidProcessoById(id);

        Set<Parte> partes = new HashSet<>();

        parteIds.forEach(
                parteId -> {
                    Parte parte = parteAuxiliar.returnValidParteSemProcessoById(parteId);
                    parte.setProcesso(processo);
                    partes.add(parte);
                });

        processo.getPartes().addAll(partes);

        processoRepositorio.save(processo);

        return ProcessoMapeamento.toResponse(processo, true);
    }
}
