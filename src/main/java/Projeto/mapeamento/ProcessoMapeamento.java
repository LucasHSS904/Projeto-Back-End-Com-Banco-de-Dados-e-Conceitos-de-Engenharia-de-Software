package Projeto.mapeamento;

import Projeto.controles.parte.ParteResposta;
import Projeto.controles.processo.ProcessoRequisiçao;
import Projeto.controles.processo.ProcessoResposta;
import Projeto.entidades.Processo;

import java.util.Set;
import java.util.stream.Collectors;

public class ProcessoMapeamento {

    public static Processo toEntity(ProcessoRequisiçao request) {

        Processo entity = new Processo();

        updateEntity(entity, request);

        return entity;
    }

    public static void updateEntity(Processo entity, ProcessoRequisiçao request) {

        entity.setNumero(request.getNumero());
    }

    public static ProcessoResposta toResponse(Processo entity, boolean mapPartes) {

        if (entity == null) {
            return null;
        }

        ProcessoResposta response = new ProcessoResposta();

        response.setId(entity.getId());
        response.setNumero(entity.getNumero());


        if (mapPartes && entity.getPartes() != null) {
            Set<ParteResposta> parteRespons =
                    entity.getPartes().stream()
                            .map(parte -> ParteMapeamento.toResponse(parte, false))
                            .collect(Collectors.toSet());
            response.setPartes(parteRespons);
        }

        response.setDataHoraCriacao(entity.getDataHoraCriacao());

        return response;
    }
}
