package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "INGREDIENTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Ingrediente.IngredienteID.class)
public class Ingrediente {
    @EqualsAndHashCode
    public static @Data class IngredienteID {
        private String nome;
        private Drink drink;
    }

    @Id
    @Column(name = "nome_ingrediente", nullable = false)
    private String nome;

    @Column(name = "quantidade")
    private Double quantidade;

    @ManyToOne(optional = false)
    @JoinColumn(name = "unidade_medida")
    private UnidadeMedida unidadeMedida;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_drink")
    private Drink drink;
}
