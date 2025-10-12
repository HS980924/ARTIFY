package com.born.artify.domain.collections.entity;

import java.util.UUID;

import com.born.artify.domain.user.entity.User;
import jakarta.persistence.*;
import jakarta.persistence.Entity;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "Collections") // 예약어 처리
public class Collections {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coll_id")
    private long coll_id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user; // b_id가 참조하는 다른 엔티티

    @Column(name = "temperature")
    private double temperature;

    @Column(name = "top_p")
    private double top_p;

    @Column(name = "frequency_penalty")
    private double frequency_penalty;

    @Column(name = "presence_penalty")
    private double presence_penalty;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

}
