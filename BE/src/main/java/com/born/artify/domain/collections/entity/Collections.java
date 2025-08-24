package com.born.artify.domain.collections.entity;

import java.util.UUID;

import com.born.artify.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "Collections") // 예약어 처리
public class Collections {

    @Id
    @Column(name = "coll_uuid", columnDefinition = "BINARY(16)")
    private UUID coll_uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // b_id가 참조하는 다른 엔티티

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UUID getColl_uuid() {
        return coll_uuid;
    }

    public void setColl_uuid(UUID coll_uuid) {
        this.coll_uuid = coll_uuid;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
