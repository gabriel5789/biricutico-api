package com.biricutico.drink.entity;

public enum UnidadeMedida {
    UNIDADE, LITRO, SHOT, A_GOSTO, ML;

    public static String getStringValue(UnidadeMedida unidadeMedida) {
        return unidadeMedida.name();
    }

    public static UnidadeMedida enumValueOf(String unidadeMedida) {
        try {
            return valueOf(unidadeMedida);
        } catch (IllegalArgumentException e) {
            throw new InvalidDomainObjectError();
        }
    }
}
