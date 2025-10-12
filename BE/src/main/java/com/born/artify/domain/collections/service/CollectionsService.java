package com.born.artify.domain.collections.service;

import com.born.artify.domain.collections.entity.Collections;
import com.born.artify.domain.collections.repository.CollectionsRepository;
import com.born.artify.domain.translations.dto.TranslateReqDTO;
import com.born.artify.domain.translations.dto.TranslateResDTO;
import com.born.artify.domain.translations.entity.Translation;
import com.born.artify.domain.translations.repository.TranslationsRepository;
import com.born.artify.domain.user.entity.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class CollectionsService {

    private final CollectionsRepository collectionsRepository;

    public CollectionsService(CollectionsRepository collectionsRepository) {
        this.collectionsRepository = collectionsRepository;
    }

    public Collections getCollections(long id) {

        return collectionsRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Collections not found"));
    }

    @Transactional
    public Collections createCollection(User user, TranslateReqDTO translateReqDTO, LocalDateTime created_at) {

        Collections collections = new Collections();

        collections.setUser(user);
        collections.setTemperature(translateReqDTO.getInfo().getParams().getTemperature());
        collections.setTop_p(translateReqDTO.getInfo().getParams().getTop_p());
        collections.setFrequency_penalty(translateReqDTO.getInfo().getParams().getFrequency_penalty());
        collections.setPresence_penalty(translateReqDTO.getInfo().getParams().getPresence_penalty());
        collections.setCreatedAt(created_at);

        return collectionsRepository.save(collections);
    }
}
