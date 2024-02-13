package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "UTENSILIO")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Utensilio.UtensilioID.class)
public class Utensilio {
    @EqualsAndHashCode
    public static @Data class UtensilioID {
        private String nome;
        private Drink drink;
    }

    @Id
    @Column(name = "nome_utensilio", nullable = false)
    private String nome;

    @Id
    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_drink")
    private Drink drink;
}
