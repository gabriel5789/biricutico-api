package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "UTENSILIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Utensilio {
    @Id
    @Column(name = "nome_utensilio", nullable = false)
    private String nome;

    @ManyToOne
    private Drink drink;
}
