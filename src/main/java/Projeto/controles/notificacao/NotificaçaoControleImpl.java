package Projeto.controles.notificacao;

import Projeto.entidades.Notificaçao;
import Projeto.serviços.notificacao.NotificacaoServiço;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/notificacoes")
@Tag(name = "Notificaçao", description = "API para gerenciamento de notificacoes")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class NotificaçaoControleImpl implements NotificaçaoControle {

    private final NotificacaoServiço notificacaoServiço;

    @GetMapping("/realizadas")
    @Operation(summary = "Retorna todas as notificações já realizadas", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> getAllNotificados() {
        Set<Notificaçao> notificacoes = notificacaoServiço.getAllNotificados();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/pendentes")
    @Operation(summary = "Retorna todas as notificações pendentes", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> getAllNaoNotificados() {
        Set<Notificaçao> notificacoes = notificacaoServiço.getAllNaoNotificados();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/ecarta")
    @Operation(summary = "Exibe todas as notificações pendentes a serem executadas pelo E-Carta", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> getNotificacoesECarta() {
        Set<Notificaçao> notificacoes = notificacaoServiço.getNotificacoesECarta();
        return ResponseEntity.ok(notificacoes);
    }

    @PutMapping("/notificar-e-carta")
    @Operation(summary = "Verifica se existem no mínimo 10 notificações a serem enviadas pelo E-Carta, caso sim, envia as notificações.", method = "PUT")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> notificarByECarta() {
        Set<Notificaçao> notificacoes = notificacaoServiço.notificarByECarta();
        return ResponseEntity.ok(notificacoes);
    }

    @GetMapping("/dje")
    @Operation(summary = "Exibe todas as notificações pendentes a serem realizadas pelo DJE.", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> getNotificacoesDje() {
        Set<Notificaçao> notificacoes = notificacaoServiço.getNotificacoesDje();
        return ResponseEntity.ok(notificacoes);
    }

    @PutMapping("/notificar-dje")
    @Operation(summary = "Efetiva todas as notificações pendentes no DJE", method = "PUT")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> notificarByDje() {
        Set<Notificaçao> notificacoes = notificacaoServiço.notificarByDje();
        return ResponseEntity.ok(notificacoes);
    }

    @PostMapping("/notificar-processo/{processoId}")
    @Operation(summary = "Registra notificações para todas as partes do processo.", method = "GET")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Set<Notificaçao>> notifyPartesByProcesso(Long processoId) {
        Set<Notificaçao> notificacoes = notificacaoServiço.notifyPartesByProcesso(processoId);
        return ResponseEntity.ok(notificacoes);
    }
}
