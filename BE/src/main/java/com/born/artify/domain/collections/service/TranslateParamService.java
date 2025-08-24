package com.born.artify.domain.collections.service;

import com.born.artify.domain.collections.entity.TranslateParam;
import com.born.artify.domain.collections.repository.TranslateParamRepository;
import java.util.UUID;

public class TranslateParamService {
    private final TranslateParamRepository transMongoRepo;

    public TranslateParamService(TranslateParamRepository transMongoRepo) {
        this.transMongoRepo = transMongoRepo;
    }

    public void saveDoc() {
        TranslateParam transParam = new TranslateParam();
        transParam.setCollUuid(UUID.randomUUID());
        transParam.setTemperature(0.0);
        transParam.setFrequency_penalty(0.0);
        transParam.setPresence_penalty(0.0);
        transParam.setTop_p(0.0);
        transMongoRepo.save(transParam);
    }

    public TranslateParam getTranslateParam(UUID uuid) {
        return transMongoRepo.findByCollUuid(uuid)
                .orElseThrow(() -> new RuntimeException("Not found"));
    }
}
