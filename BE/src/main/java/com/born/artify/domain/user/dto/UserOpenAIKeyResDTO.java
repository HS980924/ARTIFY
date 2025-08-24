package com.born.artify.domain.user.dto;

public class UserOpenAIKeyResDTO {

    private final String openAiKey;

    public UserOpenAIKeyResDTO(String openAiKey) {
        this.openAiKey = openAiKey;
    }

    public String getOpenAiKey() {
        return openAiKey;
    }
}
