package com.born.artify.domain.trend.controller;

import com.born.artify.domain.dictionary.dto.DictResDTO;
import com.born.artify.domain.dictionary.entity.dictionary;
import com.born.artify.domain.translations.dto.TranslateCntResDTO;
import com.born.artify.domain.translations.service.TranslationService;
import com.born.artify.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/api/trend")
public class TrendController {
    private final TranslationService translationService;

    public TrendController(TranslationService translationService) {
        this.translationService = translationService;
    }


    @GetMapping()
    public ResponseEntity<Map<String, Object>> getTranslateWeeklyCnt() {
        long userId = SecurityUtil.getCurrentUserIdAsInt();

        Map<LocalDate, Long> resultMap = translationService.getWeeklyTranslationCount(userId);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "1주일 트렌드 조회 성공");
        result.put("data", resultMap);

        return ResponseEntity.ok().body(result);
    }
}
