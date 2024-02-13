package com.biricutico.drink.interactor;

import com.biricutico.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public @Data class DrinkRegisterResponse extends BaseResponse<DrinkRegisterResponse.DrinkDTO> {
    @AllArgsConstructor
    @NoArgsConstructor
    public static @Data class IngredienteDTO {
        private String nome;
        private String unidadeMedida;
        private Double quantidade;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    public static @Data class DrinkDTO {
        private Long id;
        private String nome;
        private String foto; /* url do link da imagem no s3 da AWS */
        private String descricao;
        private String instrucoesPreparo;
        private List<String> utensilios;
        private List<IngredienteDTO> ingredientes;
        private Map<String, String> tags;
        private String teorAlcoolico;
        private String taca;
    }
}
