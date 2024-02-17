package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "UNIDADE_MEDIDA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeMedida {
    @Id
    @Column(name = "unidade_medida", insertable = false)
    private String nome;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "unidadeMedida")
    private List<Ingrediente> ingredientes;
}
