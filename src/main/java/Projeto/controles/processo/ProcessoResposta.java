package Projeto.controles.processo;

import Projeto.controles.parte.ParteResposta;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ProcessoResposta {

    private Long id;

    private String numero;

    @JsonIgnoreProperties({"processo"})
    private Set<ParteResposta> partes;

    private LocalDateTime dataHoraCriacao;
}
