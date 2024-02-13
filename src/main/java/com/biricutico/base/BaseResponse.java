package com.biricutico.base;

import lombok.Data;

public abstract @Data class BaseResponse<D> {
    protected ResponseHeader header;
    protected D data;
}
