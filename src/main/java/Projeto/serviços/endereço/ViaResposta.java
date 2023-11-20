package Projeto.serviços.endereço;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
public class ViaResposta {

    private String cep;

    private String logradouro;

    private String bairro;

    private String localidade;

    private String uf;

    private boolean erro;
}
