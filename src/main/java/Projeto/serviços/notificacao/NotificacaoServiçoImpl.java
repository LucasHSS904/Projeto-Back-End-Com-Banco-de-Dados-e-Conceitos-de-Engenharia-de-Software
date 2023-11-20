package Projeto.serviços.notificacao;

import Projeto.entidades.Notificaçao;
import Projeto.entidades.Parte;
import Projeto.entidades.Processo;
import Projeto.entidades.enums.TipoNotificacao;
import Projeto.repositorio.NotificaçaoRepositorio;
import Projeto.serviços.processo.ProcessoAuxiliar;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class NotificacaoServiçoImpl implements NotificacaoServiço {

    private final NotificaçaoRepositorio notificaçaoRepositorio;

    private final JavaMailSender javaMailSender;

    private final ProcessoAuxiliar processoAuxiliar;

    private static SimpleMailMessage getSimpleMailMessage(Parte parte) {
        String to = parte.getEmail();
        String subject = "Notificação PJE - Processo de Número: " + parte.getProcesso().getNumero();
        String message = "Olá " + parte.getNomeCompleto() + ",\n\n"
                + "Esta é a mensagem da notificação.\n"
                + "Mensagem: Houve notificação sobre o processo judicial. Acesse o processo na plataforma do PJE.";

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(to);
        mailMessage.setSubject(subject);
        mailMessage.setText(message);
        return mailMessage;
    }

    @Override
    public Set<Notificaçao> getAllNotificados() {
        return notificaçaoRepositorio.findByNotificadoTrue();
    }

    @Override
    public Set<Notificaçao> getAllNaoNotificados() {
        return notificaçaoRepositorio.findByNotificadoFalse();
    }

    @Override
    public Set<Notificaçao> getNotificacoesECarta() {
        return notificaçaoRepositorio.findByTipoNotificacaoAndNotificadoFalse(TipoNotificacao.E_CARTA);
    }

    @Override
    public Set<Notificaçao> notificarByECarta() {
        Set<Notificaçao> notificacoesECarta = new HashSet<>(notificaçaoRepositorio.findByTipoNotificacaoAndNotificadoFalse(TipoNotificacao.E_CARTA));

        if (notificacoesECarta.size() >= 10) {
            for (Notificaçao notificaçao : notificacoesECarta) {
                notificaçao.setNotificado(true);
                notificaçaoRepositorio.save(notificaçao);
            }

            return notificacoesECarta;
        }

        throw new RuntimeException("O número mínimo de notificações para serem enviadas por essa modalidade é 10. " +
                "No momento o sistema tem apenas: " + notificacoesECarta.size());
    }

    @Override
    public Set<Notificaçao> getNotificacoesDje() {
        return notificaçaoRepositorio.findByTipoNotificacaoAndNotificadoFalse(TipoNotificacao.DJE);
    }

    @Override
    public Set<Notificaçao> notificarByDje() {
        Set<Notificaçao> notificacoesDje = new HashSet<>();

        for (Notificaçao notificaçao : notificaçaoRepositorio.findByTipoNotificacaoAndNotificadoFalse(TipoNotificacao.DJE)) {
            notificacoesDje.add(notificaçao);
            notificaçao.setNotificado(true);
            notificaçaoRepositorio.save(notificaçao);
        }

        return notificacoesDje;
    }

    @Override
    public Set<Notificaçao> notifyPartesByProcesso(Long processoId) {
        Processo processo = processoAuxiliar.returnValidProcessoById(processoId);

        Set<Notificaçao> notificacoes = new HashSet<>();

        for (Parte parte : processo.getPartes()) {
            boolean notificou = false;

            if (parte.getNumeroEndereco() != null
                    && !parte.getNumeroEndereco().isBlank()
                    && parte.getEndereço() != null) {
                Notificaçao notificaçaoCorreios = new Notificaçao();

                notificaçaoCorreios.setParte(parte);
                notificaçaoCorreios.setNotificado(false);
                notificaçaoCorreios.setTipoNotificacao(TipoNotificacao.E_CARTA);

                parte.getNotificacoes().add(notificaçaoCorreios);

                notificacoes.add(notificaçaoCorreios);

                notificaçaoRepositorio.save(notificaçaoCorreios);

                notificou = true;
            }

            if (parte.getEmail() != null
                    && !parte.getEmail().isBlank()
                    && !notificou) {
                Notificaçao notificaçaoEmail = new Notificaçao();

                notificaçaoEmail.setParte(parte);
                notificaçaoEmail.setNotificado(true);
                notificaçaoEmail.setTipoNotificacao(TipoNotificacao.EMAIL);

                parte.getNotificacoes().add(notificaçaoEmail);

                SimpleMailMessage mailMessage = getSimpleMailMessage(parte);

                javaMailSender.send(mailMessage);

                notificaçaoRepositorio.save(notificaçaoEmail);

                notificacoes.add(notificaçaoEmail);

                notificou = true;
            }

            if (!notificou) {
                Notificaçao notificaçaoDje = new Notificaçao();

                notificaçaoDje.setParte(parte);
                notificaçaoDje.setNotificado(false);
                notificaçaoDje.setTipoNotificacao(TipoNotificacao.DJE);

                parte.getNotificacoes().add(notificaçaoDje);

                notificaçaoRepositorio.save(notificaçaoDje);

                notificacoes.add(notificaçaoDje);
            }
        }

        return notificacoes;
    }
}
