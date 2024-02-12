package com.biricutico.drink.interactor;

import com.biricutico.base.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public @Data class DrinkSearchResponse extends BaseResponse<List<DrinkSearchResponse.DrinkDTO>> {

    @AllArgsConstructor
    @NoArgsConstructor
    public static @Data class DrinkDTO {
        private Long id;
        private String nome;
        private String foto;
        private String descricao;
    }
}
