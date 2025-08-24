package com.born.artify.domain.translation.controller;

import com.born.artify.domain.translation.dto.TranslateReqDTO;
import com.born.artify.domain.translation.service.TranslationService;
import com.born.artify.domain.user.dto.UserOpenAIKeyReqDTO;
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

import java.util.HashMap;
import java.util.Map;

@Controller
public class TranslationController {


    @Value("${translateServer.url}")
    private String translateServer;

    private final TranslationService translationService;
    private final RestTemplate restTemplate = new RestTemplate();

    private final UserService userService;

    public TranslationController(TranslationService translationService, UserService userService) {
        this.translationService = translationService;
        this.userService = userService;
    }

    @PostMapping("/api/content/trans")
    public ResponseEntity<Map<String, Object>> setUserOpenAIKey(@RequestBody TranslateReqDTO dto) {
        int userId = SecurityUtil.getCurrentUserIdAsInt();
        User user = userService.getUser(userId);
        dto.getInfo().getParams().setOPENAI_API_KEY(user.getOpen_key());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON); // JSON 타입 지정

        System.out.println(dto);
        // Map<String, Object> transMap = new HashMap<>();
        // transMap.put("info", dto);

        // System.out.println(transMap);

        HttpEntity<TranslateReqDTO> entity = new HttpEntity<>(dto, headers);

        System.out.println(entity);

        ResponseEntity<Object> response = restTemplate.postForEntity(translateServer, entity, Object.class);

        // 여기까지

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "번역 요청 성공");
        result.put("data", response.getBody());

        return ResponseEntity.ok().body(result);
    }
}
