package com.born.artify.domain.collections.repository;

import com.born.artify.domain.collections.entity.Collections;
import com.born.artify.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface CollectionsRepository extends JpaRepository<Collections, Long> {
    Optional<Collections> findById(long id);
}
