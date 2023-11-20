package Projeto.controles.notificacao;

import Projeto.entidades.Notificaçao;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface NotificaçaoControle {

    ResponseEntity<Set<Notificaçao>> getAllNotificados();

    ResponseEntity<Set<Notificaçao>> getAllNaoNotificados();

    ResponseEntity<Set<Notificaçao>> getNotificacoesECarta();

    ResponseEntity<Set<Notificaçao>> notificarByECarta();

    ResponseEntity<Set<Notificaçao>> getNotificacoesDje();

    ResponseEntity<Set<Notificaçao>> notificarByDje();

    ResponseEntity<Set<Notificaçao>> notifyPartesByProcesso(Long processoId);
}
