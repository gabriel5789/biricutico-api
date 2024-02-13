package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "TAG")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(Tag.TagID.class)
public class Tag {
    @EqualsAndHashCode
    public static @Data class TagID {
        private String chave;
        private String valor;
        private Drink drink;
    }

    @Id
    @Column(name = "chave", nullable = false)
    private String chave;

    @Id
    @Column(name = "valor", nullable = false)
    private String valor;

    @Id
    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_drink")
    private Drink drink;
}
