package Projeto.controles.parte;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class ParteRequisiçao {

    @NotBlank(message = "O campo NOME não pode estar vazio.")
    private String nomeCompleto;

    @Pattern(
            regexp = "\\d{11}|\\d{14}",
            message = "O campo DOCUMENTO deve conter 11 dígitos (CPF) ou 14 dígitos (CNPJ).")
    private String documento;

    // opcional
    @NotNull
    private String email;

    // opcional
    @NotNull
    private String cep;

    // opcional
    @NotNull
    private String numeroEndereco;

    // opcional
    @NotNull
    private String complementoEndereco;
}
