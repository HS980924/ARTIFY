package com.born.artify.domain.translations.dto;

public class TranslateReqDTO {
    private final InfoReqDTO info;

    public TranslateReqDTO(InfoReqDTO info) {
        this.info = info;
    }

    public InfoReqDTO getInfo() {
        return info;
    }
}
