package com.born.artify.domain.translations.service;

import com.born.artify.domain.collections.entity.Collections;
import com.born.artify.domain.translations.dto.TranslateCntResDTO;
import com.born.artify.domain.translations.dto.TranslateResDTO;
import com.born.artify.domain.translations.entity.Translation;
import com.born.artify.domain.translations.repository.TranslationsRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class TranslationService {

    private final TranslationsRepository translationsRepository;

    public TranslationService(TranslationsRepository translationsRepository) {
        this.translationsRepository = translationsRepository;
    }

    @Transactional
    public void createTranslation(TranslateResDTO translateResDTO, Collections collection, long userId) {

        Translation translation  = new Translation();

        translation.setTran_uuid(translateResDTO.getUuid());
        translation.setCollections(collection);
        translation.setUser_id(userId);
        translation.setOriginal_text(translateResDTO.getContent().getBefore());
        translation.setTranslated_text(translateResDTO.getContent().getAfter());
        translation.setCreated_at(translateResDTO.getCreated_at());

        translationsRepository.save(translation);
    }


    public Map<LocalDate, Long> getWeeklyTranslationCount(long userId) {
        LocalDateTime startDate = LocalDate.now().minusDays(6).atStartOfDay();

        List<TranslateCntResDTO> counts = translationsRepository.countTranslationsByDate(userId, startDate);

        // 날짜 순서 유지, 없는 날짜는 0
        Map<LocalDate, Long> resultMap = new LinkedHashMap<>();
        for (int i = 0; i < 7; i++) {
            resultMap.put(LocalDate.now().minusDays(6 - i), 0L);
        }

        for (TranslateCntResDTO dto : counts) {
            resultMap.put(dto.getDate().toLocalDate(), dto.getCount());
        }

        return resultMap;
    }
}
