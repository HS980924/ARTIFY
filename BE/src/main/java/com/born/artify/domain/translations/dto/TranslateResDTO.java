package com.born.artify.domain.translations.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class TranslateResDTO {
    private UUID uuid;
    private TranslateContent content;
    private LocalDateTime created_at;
}
