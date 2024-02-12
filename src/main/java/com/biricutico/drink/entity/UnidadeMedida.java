package com.biricutico.drink.entity;

import lombok.Getter;

import java.util.Optional;

@Getter
public enum UnidadeMedida {

    UNIDADE("Unidade"), LITRO("Litro"), SHOT("Shot");

    private String unidadeMedida;

    UnidadeMedida(String unidadeMedida) {
        this.unidadeMedida = Optional.ofNullable(unidadeMedida).orElseThrow(InvalidDomainObjectError::new);
    }

}
