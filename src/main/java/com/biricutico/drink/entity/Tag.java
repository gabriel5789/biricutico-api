package com.biricutico.drink.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class Tag {
    private String key;
    private String value;

    private Tag(String key, String value) {
        this.key = Optional.ofNullable(key).orElseThrow(InvalidDomainObjectError::new);
        this.value = Optional.ofNullable(value).orElseThrow(InvalidDomainObjectError::new);
    }

    public static Tag of(String key, String value) {
        return new Tag(key, value);
    }
}
