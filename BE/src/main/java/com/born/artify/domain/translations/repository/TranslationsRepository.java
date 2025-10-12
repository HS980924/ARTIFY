package com.born.artify.domain.translations.repository;

import com.born.artify.domain.translations.dto.TranslateCntResDTO;
import com.born.artify.domain.translations.entity.Translation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;


public interface TranslationsRepository extends JpaRepository<Translation, UUID> {
    @Query("SELECT new com.born.artify.domain.translations.dto.TranslateCntResDTO(" +
            "CAST(t.created_at AS date), COUNT(t)) " +
            "FROM Translation t " +
            "WHERE t.user_id = :userId " +
            "AND t.created_at >= :startDate " +
            "GROUP BY CAST(t.created_at AS date) " +
            "ORDER BY CAST(t.created_at AS date)")
    List<TranslateCntResDTO> countTranslationsByDate(
            @Param("userId") long userId,
            @Param("startDate") LocalDateTime startDate);
}
