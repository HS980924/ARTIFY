package com.born.artify.domain.dictionary.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class DictResDTO {
    private Long dictId;

    private String key;

    private String value;

    private LocalDateTime createdAt;


    public DictResDTO(Long dictId, String key, String value, LocalDateTime createdAt) {
        this.dictId = dictId;
        this.key = key;
        this.value = value;
        this.createdAt = createdAt;
    }

    public Long getDictId() {
        return dictId;
    }

    public void setDictId(Long dictId) {
        this.dictId = dictId;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
