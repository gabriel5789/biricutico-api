package com.biricutico.drink.interactor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public @Data class DrinkSearchRequest {
    @NoArgsConstructor
    @AllArgsConstructor
    public static @Data class FilterOption {
        private String field;
        private String operation;
        private Object value;
    }

    private String dataOption;
    private List<FilterOption> filterOptions;

    /* Paginação */
    private Integer page;
    private Integer pageSize;
}

