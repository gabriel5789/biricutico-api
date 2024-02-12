package com.biricutico.drink.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Utensilio {

    private String nome;

    private Utensilio(String nome) {
        this.nome = Optional.ofNullable(nome).orElseThrow(InvalidDomainObjectError::new);
    }

    public static Utensilio of(String nome) {
        return new Utensilio(nome);
    };
}
