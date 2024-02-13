package com.biricutico.base;

import lombok.Data;

public @Data class BaseError {
    private Integer errorCode;
    private String errorMessage;

    public BaseError(Integer errorCode, String errorMessage) {
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
