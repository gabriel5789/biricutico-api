package com.biricutico.base;

import lombok.Data;

public @Data class ResponseHeader {
    private int statusCode;
    private BaseError error;

    public ResponseHeader(int statusCode, BaseError error) {
        this.statusCode = statusCode;
        this.error = error;
    }
}
