package Projeto.entidades.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum TipoNotificacao {
    E_CARTA("Correios"),

    EMAIL("E-mail"),

    DJE("Diário da Justiça Eletrônico");

    private final String value;
}
