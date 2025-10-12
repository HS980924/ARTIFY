package com.born.artify.domain.translations.dto;

import lombok.Data;

import java.sql.Date;

@Data
public class TranslateCntResDTO {
    private Date date;
    private long count;

    public TranslateCntResDTO(Date date, long count) {
        this.date = date;
        this.count = count;
    }
}
