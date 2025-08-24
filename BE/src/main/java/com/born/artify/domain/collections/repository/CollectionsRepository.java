package com.born.artify.domain.collections.repository;

import com.born.artify.domain.collections.entity.Collections;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CollectionsRepository extends JpaRepository<Collections, UUID> {
}
