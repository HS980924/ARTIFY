package com.born.artify.domain.dictionary.service;


import com.born.artify.domain.dictionary.dto.CreateDictReqDTO;
import com.born.artify.domain.dictionary.repository.DictionaryRepository;
import com.born.artify.domain.dictionary.entity.dictionary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DictionaryService {

    private final DictionaryRepository dictRepository;

    public DictionaryService(DictionaryRepository dictRepository) {
        this.dictRepository = dictRepository;
    }

    @Transactional
    public dictionary createDict(CreateDictReqDTO request, long userId) {

        dictionary dict = new dictionary();
        dict.setUserId(userId);
        dict.setFrom_key(request.getKey());
        dict.setTo_value(request.getValue());

        return dictRepository.save(dict); // 저장
    }


    public List<dictionary> getDictionary(long userId) {

        return dictRepository.findByUserId(userId);
    }

    @Transactional
    public long  deleteDictionary(long dictId)
    {
        return dictRepository.deleteByDictId(dictId);
    }

}
