package com.biricutico.drink.database;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UNIDADE_MEDIDA")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UnidadeMedida {
    @Id
    @Column(name = "unidade_medida")
    private String nome;

}
