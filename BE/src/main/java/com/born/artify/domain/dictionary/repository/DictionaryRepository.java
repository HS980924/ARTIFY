package com.born.artify.domain.dictionary.repository;

import com.born.artify.domain.dictionary.entity.dictionary;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DictionaryRepository extends JpaRepository<dictionary, Long> {
    List<dictionary> findByUserId(Long userId);
    long deleteByDictId(long dictId);

}
