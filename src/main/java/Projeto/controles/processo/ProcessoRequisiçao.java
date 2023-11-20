package Projeto.controles.processo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ProcessoRequisiçao {

    @NotBlank(message = "O campo número não pode estar vazio.")
    @Pattern(regexp = "\\d{7}-\\d{2}\\.\\d{4}\\.\\d{1}\\.\\d{2}\\.\\d{4}",
            message = "Formato de número de processo inválido. Deve seguir o padrão: 9999999-99.9999.9.99.9999")
    String numero;
}
