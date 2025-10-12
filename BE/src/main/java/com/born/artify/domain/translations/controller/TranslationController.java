package com.born.artify.domain.translations.controller;

import com.born.artify.domain.collections.entity.Collections;
import com.born.artify.domain.collections.service.CollectionsService;
import com.born.artify.domain.dictionary.entity.dictionary;
import com.born.artify.domain.dictionary.service.DictionaryService;
import com.born.artify.domain.translations.dto.TranslateReqDTO;
import com.born.artify.domain.translations.dto.TranslateResDTO;
import com.born.artify.domain.translations.service.TranslationService;
import com.born.artify.domain.user.entity.User;
import com.born.artify.domain.user.service.UserService;
import com.born.artify.util.SecurityUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class TranslationController {


    @Value("${translateServer.url}")
    private String translateServer;

    private final TranslationService translationService;
    private final DictionaryService dictionaryService;
    private final CollectionsService collectionsService;

    private final RestTemplate restTemplate = new RestTemplate();

    private final UserService userService;

    public TranslationController(TranslationService translationService, DictionaryService dictionaryService, CollectionsService collectionsService, UserService userService) {
        this.translationService = translationService;
        this.dictionaryService = dictionaryService;
        this.collectionsService = collectionsService;
        this.userService = userService;
    }

    @PostMapping("/api/content/trans")
    public ResponseEntity<Map<String, Object>> setUserOpenAIKey(@RequestBody TranslateReqDTO dto) {
        int userId = SecurityUtil.getCurrentUserIdAsInt();
        User user = userService.getUser(userId);
        dto.getInfo().getParams().setOPENAI_API_KEY(user.getOpen_key());

        List<dictionary> dictionaryList = dictionaryService.getDictionary(userId);

        for(dictionary dict : dictionaryList)
            dto.getInfo().addTemplateVariable(dict.getFrom_key(), dict.getTo_value());


        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // JSON 타입 지정


        HttpEntity<TranslateReqDTO> entity = new HttpEntity<>(dto, headers);

        System.out.println(translateServer);

        ResponseEntity<TranslateResDTO> response = restTemplate.postForEntity(translateServer, entity, TranslateResDTO.class);

        TranslateResDTO translateResDTO = response.getBody();

        if (translateResDTO != null) {
            translateResDTO.setCreated_at(LocalDateTime.now()); //  현재 시간 추가
            Collections collection = collectionsService.createCollection(user, dto, translateResDTO.getCreated_at());
            translationService.createTranslation(translateResDTO, collection, userId);
        }


        Map<String, Object> result = new HashMap<>();

        if (translateResDTO != null) {
            result.put("status", 200);
            result.put("msg", "번역 요청 성공");
            result.put("data", response.getBody());
        }else{
            result.put("status", 500);
            result.put("msg", "번역 요청 실패");
            result.put("data", null);
        }


        return ResponseEntity.ok().body(result);
    }
}
