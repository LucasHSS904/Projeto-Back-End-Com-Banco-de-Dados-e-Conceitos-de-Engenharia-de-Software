package Projeto.controles.parte;

import Projeto.controles.processo.ProcessoResposta;
import Projeto.entidades.Endereço;
import Projeto.entidades.Notificaçao;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ParteResposta {

    private Long id;

    private String nomeCompleto;

    private String documento;

    private String email;

    private String numeroEndereco;

    private String complementoEndereco;

    @JsonIgnoreProperties({"partes"})
    private Endereço endereço;

    @JsonIgnoreProperties({"partes"})
    private ProcessoResposta processo;

    @JsonIgnoreProperties({"parte"})
    private Set<Notificaçao> notificacoes;

    private LocalDateTime dataHoraCriacao;
}
