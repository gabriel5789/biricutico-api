package com.biricutico.drink.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Ingrediente {
    private String nome;
    private Double quantidade;
    private UnidadeMedida unidadeMedida;

    private Ingrediente(String nome, Double quantidade, UnidadeMedida unidadeMedida) {
        /* Obrigatórios */
        this.nome = Optional.ofNullable(nome).orElseThrow(InvalidDomainObjectError::new);
        this.unidadeMedida = Optional.ofNullable(unidadeMedida).orElseThrow(InvalidDomainObjectError::new);
        /* Obrigatório, exceto se unidade de medida for a gosto */
        this.quantidade = unidadeMedida.equals(UnidadeMedida.A_GOSTO) ?
                quantidade : Optional.ofNullable(quantidade).orElseThrow(InvalidDomainObjectError::new);
    }

    public static Ingrediente of(String nome, Double quantidade, UnidadeMedida unidadeMedida) {
        return new Ingrediente(nome, quantidade, unidadeMedida);
    }
}
