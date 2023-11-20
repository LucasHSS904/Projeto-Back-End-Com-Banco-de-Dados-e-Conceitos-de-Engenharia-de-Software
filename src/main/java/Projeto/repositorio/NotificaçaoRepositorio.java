package Projeto.repositorio;

import Projeto.entidades.Notificaçao;
import Projeto.entidades.enums.TipoNotificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface NotificaçaoRepositorio extends JpaRepository<Notificaçao, Long> {

    Set<Notificaçao> findByNotificadoFalse();

    Set<Notificaçao> findByNotificadoTrue();

    Set<Notificaçao> findByTipoNotificacaoAndNotificadoFalse(TipoNotificacao tipoNotificacao);
}

