package Projeto.mapeamento;

import Projeto.controles.parte.ParteRequisiçao;
import Projeto.controles.parte.ParteResposta;
import Projeto.controles.processo.ProcessoResposta;
import Projeto.entidades.Parte;

public class ParteMapeamento {

    public static Parte toEntity(ParteRequisiçao request) {

        Parte entity = new Parte();

        updateEntity(entity, request);

        return entity;
    }

    public static void updateEntity(Parte entity, ParteRequisiçao request) {

        entity.setNomeCompleto(request.getNomeCompleto());
        entity.setDocumento(request.getDocumento());
        entity.setEmail(request.getEmail());
        entity.setNumeroEndereco(request.getNumeroEndereco());
        entity.setComplementoEndereco(request.getComplementoEndereco());
    }

    public static ParteResposta toResponse(Parte entity, boolean mapProcesso) {

        if (entity == null) {
            return null;
        }

        ParteResposta response = new ParteResposta();

        response.setId(entity.getId());
        response.setNomeCompleto(entity.getNomeCompleto());
        response.setDocumento(entity.getDocumento());
        response.setEmail(entity.getEmail());
        response.setNumeroEndereco(entity.getNumeroEndereco());
        response.setComplementoEndereco(entity.getComplementoEndereco());

        if (mapProcesso && entity.getProcesso() != null) {
            ProcessoResposta processoResposta = ProcessoMapeamento.toResponse(entity.getProcesso(), false);
            response.setProcesso(processoResposta);
        }

        response.setNotificacoes(entity.getNotificacoes());
        response.setEndereço(entity.getEndereço());
        response.setDataHoraCriacao(entity.getDataHoraCriacao());

        return response;
    }
}
