package com.biricutico.drink.database;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "DRINK")
@SequenceGenerator(name = "sq_id_drink", allocationSize = 1, initialValue = 1, sequenceName = "sq_id_drink")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drink {
    @Id
    @GeneratedValue(generator = "sq_id_drink", strategy = GenerationType.SEQUENCE)
    @Column(name = "id_drink")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "foto")
    private String foto;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "instrucoes_preparo", length = 1024)
    private String instrucoesPreparo;

    @Column(name = "taca")
    private String taca;

    @Column(name = "teor_alcoolico")
    private String teorAlcoolico;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "drink", cascade = CascadeType.ALL)
    private List<Utensilio> utensilios;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "drink", cascade = CascadeType.ALL)
    private List<Ingrediente> ingredientes;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "drink", cascade = CascadeType.ALL)
    private List<Tag> tags;
}
