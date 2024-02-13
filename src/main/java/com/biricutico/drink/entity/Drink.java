package com.biricutico.drink.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
public class Drink {
    private Long id;
    private String nome;
    private String foto; /* Deve ser uma URL */
    private String descricao;
    private String instrucoesPreparo;
    private List<Utensilio> utensilios;
    private List<Ingrediente> ingredientes;
    private List<Tag> tags;
    private String teorAlcoolico;
    private String taca;

    private Drink(Long id, String nome, String foto, String descricao, String instrucoesPreparo, List<Utensilio> utensilios, List<Ingrediente> ingredientes, List<Tag> tags, String teorAlcoolico, String taca) {
        /* Obrigatórios */
        this.nome = Optional.ofNullable(nome).orElseThrow(InvalidDomainObjectError::new);
        this.instrucoesPreparo = Optional.ofNullable(instrucoesPreparo).orElseThrow(InvalidDomainObjectError::new);
        this.ingredientes = Optional.ofNullable(ingredientes).orElseThrow(InvalidDomainObjectError::new);
        if (this.ingredientes.isEmpty()) throw new InvalidDomainObjectError();

        /* Opcionais */
        this.utensilios = Optional.ofNullable(utensilios).orElse(new LinkedList<>());
        this.tags = Optional.ofNullable(tags).orElse(new LinkedList<>());
        this.teorAlcoolico = teorAlcoolico;
        this.taca = taca;
        this.foto = foto;
        this.descricao = descricao;
        this.id = id;
    }

    public static Drink of(Long id, String nome, String foto, String descricao, String instrucoesPreparo, List<Utensilio> utensilios, List<Ingrediente> ingredientes, List<Tag> tags, String teorAlcoolico, String taca) throws InvalidDomainObjectError {
        return new Drink(id, nome, foto, descricao, instrucoesPreparo, utensilios, ingredientes, tags, teorAlcoolico, taca);
    }
}
