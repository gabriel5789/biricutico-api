package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "INGREDIENTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Ingrediente {
    @Id
    @Column(name = "nome_ingrediente", nullable = false)
    private String nome;
    @Column(name = "quantidade", nullable = false)
    private Double quantidade;
    @Column(name = "tipo")
    private String tipo;
    @ManyToOne(optional = false)
    private UnidadeMedida unidadeMedida;
    @ManyToOne(optional = false)
    private Drink drink;
}
