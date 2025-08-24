package com.born.artify.domain.user.dto;

public class UserOpenAIKeyReqDTO {
    private final String openAiKey;

    public UserOpenAIKeyReqDTO(String openAiKey) {
        this.openAiKey = openAiKey;
    }

    public String getOpenAiKey() {
        return openAiKey;
    }
}
