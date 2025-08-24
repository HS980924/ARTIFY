package com.born.artify.domain.collections.entity;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.UUID;

@Document(collection = "TranslateParam") // Mongo 컬렉션 이름
public class TranslateParam {

    @Id
    private UUID collUuid;


    private double  temperature;
    private double  top_p;
    private double  frequency_penalty;
    private double  presence_penalty;

    public TranslateParam() {}

    public TranslateParam(UUID collUuid, double temperature, double top_p, double frequency_penalty, double presence_penalty) {
        this.collUuid = collUuid;
        this.temperature = temperature;
        this.top_p = top_p;
        this.frequency_penalty = frequency_penalty;
        this.presence_penalty = presence_penalty;
    }

    public UUID getCollUuid() {
        return collUuid;
    }

    public void setCollUuid(UUID collUuid) {
        this.collUuid = collUuid;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getTop_p() {
        return top_p;
    }

    public void setTop_p(double top_p) {
        this.top_p = top_p;
    }

    public double getFrequency_penalty() {
        return frequency_penalty;
    }

    public void setFrequency_penalty(double frequency_penalty) {
        this.frequency_penalty = frequency_penalty;
    }

    public double getPresence_penalty() {
        return presence_penalty;
    }

    public void setPresence_penalty(double presence_penalty) {
        this.presence_penalty = presence_penalty;
    }


// Getters / Setters
}
