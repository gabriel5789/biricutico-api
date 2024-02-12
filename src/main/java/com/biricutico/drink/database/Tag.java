package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "TAG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tag {
    @Id
    @Column(name = "chave", nullable = false)
    private String chave;

    @Id
    @Column(name = "valor", nullable = false)
    private String valor;

    @Id
    @ManyToOne(optional = false)
    private Drink drink;
}
