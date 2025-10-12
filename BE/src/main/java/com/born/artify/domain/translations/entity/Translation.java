package com.born.artify.domain.translations.entity;

import com.born.artify.domain.collections.entity.Collections;
import com.born.artify.domain.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "translations")
public class Translation {
    @Id
    @Column(name = "tran_uuid", columnDefinition = "BINARY(16)")
    private UUID tran_uuid;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "coll_id", nullable = false)
    private Collections collections; // b_id가 참조하는 다른 엔티티

    @Column(name = "user_id")
    private long user_id;

    @Column(name = "original_text")
    private String original_text;

    @Column(name = "translated_text")
    private String translated_text;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime created_at;
}
