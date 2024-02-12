package com.biricutico.base;

import lombok.Data;

public abstract @Data class BaseResponse<D> {
    public static @Data class BaseError {
        private Integer errorCode;
        private String errorMessage;

        public BaseError(Integer errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
    public static @Data class ResponseHeader {
        private int statusCode;
        private BaseError error;

        public ResponseHeader(int statusCode, BaseError error) {
            this.statusCode = statusCode;
            this.error = error;
        }
    }
    protected ResponseHeader header;
    protected D data;
}
