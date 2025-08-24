package com.born.artify.domain.translation.dto;

public class LanguageReqDTO {

    private final String from;
    private final String to;

    public LanguageReqDTO(String from, String to) {
        this.from = from;
        this.to = to;
    }

    public String getTo() {
        return to;
    }

    public String getFrom() {
        return from;
    }
}
