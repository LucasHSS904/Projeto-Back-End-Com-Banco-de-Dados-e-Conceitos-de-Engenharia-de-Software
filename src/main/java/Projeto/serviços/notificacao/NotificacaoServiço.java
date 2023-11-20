package Projeto.serviços.notificacao;

import Projeto.entidades.Notificaçao;

import java.util.Set;

public interface NotificacaoServiço {

    Set<Notificaçao> getAllNotificados();

    Set<Notificaçao> getAllNaoNotificados();

    Set<Notificaçao> getNotificacoesECarta();

    Set<Notificaçao> notificarByECarta();

    Set<Notificaçao> getNotificacoesDje();

    Set<Notificaçao> notificarByDje();

    Set<Notificaçao> notifyPartesByProcesso(Long processoId);
}
