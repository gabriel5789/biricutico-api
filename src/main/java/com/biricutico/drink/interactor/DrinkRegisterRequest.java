package com.biricutico.drink.interactor;

import lombok.Data;

import java.util.List;
import java.util.Map;

public @Data class DrinkRegisterRequest {
    public static @Data class IngredienteDTO {
        private String nome;
        private String unidadeMedida;
        private Double quantidade;
    }

    private String nome;
    private String foto; /* byte array convertido pra base64string */
    private String descricao;
    private String instrucoesPreparo;
    private List<String> utensilios;
    private List<IngredienteDTO> ingredientes;
    private Map<String, String> tags;
    private String teorAlcoolico;
    private String taca;
}
