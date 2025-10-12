package com.born.artify.domain.translations.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ParamsReqDTO {

    @JsonProperty("OPENAI_API_KEY")
    private String OPENAI_API_KEY;
    private final double temperature;
    private final double top_p;
    private final double frequency_penalty;
    private final double presence_penalty;

    public ParamsReqDTO(String OPENAI_API_KEY, double temperature, double top_p, double frequency_penalty, double presence_penalty) {
        this.OPENAI_API_KEY = OPENAI_API_KEY;
        this.temperature = temperature;
        this.top_p = top_p;
        this.frequency_penalty = frequency_penalty;
        this.presence_penalty = presence_penalty;
    }

    public String getOPENAI_API_KEY() {
        return OPENAI_API_KEY;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getTop_p() {
        return top_p;
    }

    public double getFrequency_penalty() {
        return frequency_penalty;
    }

    public double getPresence_penalty() {
        return presence_penalty;
    }

    public void setOPENAI_API_KEY(String apiKey)
    {
        this.OPENAI_API_KEY = apiKey;
    }
}
