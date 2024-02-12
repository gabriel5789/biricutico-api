package com.biricutico.drink.interactor;

import lombok.Data;

import java.util.List;

public @Data class DrinkSearchRequest {
    public static @Data class FilterOption {
        private String field;
        private String operation;
        private String value;
    }

    private String dataOption;
    private List<FilterOption> filterOptions;

    /* Paginação */
    private Integer page;
    private Integer pageSize;
}

