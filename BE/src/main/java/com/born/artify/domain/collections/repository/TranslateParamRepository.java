package com.born.artify.domain.collections.repository;

import com.born.artify.domain.collections.entity.TranslateParam;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;
import java.util.UUID;

public interface TranslateParamRepository extends MongoRepository<TranslateParam, UUID> {
    Optional<TranslateParam> findByCollUuid(UUID uuid);
}
