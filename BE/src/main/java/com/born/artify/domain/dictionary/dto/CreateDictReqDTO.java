package com.born.artify.domain.dictionary.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDictReqDTO {
    private String key;

    private String value;

    public CreateDictReqDTO(String value, String key) {
        this.value = value;
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
