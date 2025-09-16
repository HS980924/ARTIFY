package com.born.artify.domain.user.controller;
import com.born.artify.domain.user.dto.*;
import com.born.artify.domain.user.service.UserService;
import com.born.artify.domain.user.entity.User;

import com.born.artify.util.MaskingUtil;
import com.born.artify.util.SecurityUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import jakarta.validation.Valid;

import java.util.HashMap;
import java.util.Map;

@Controller
//@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // 이거 삭제?
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/api/user/me")
    public ResponseEntity<Map<String, Object>> getUserInfo() {
        int userId = SecurityUtil.getCurrentUserIdAsInt();

        User user = userService.getUser(userId);

        UserInfoResDTO res = new UserInfoResDTO(
                user.getProfile_url(),
                user.getUpdated_at(),
                user.getCreatedAt(),
                user.getCollection_id(),
                user.getEmail(),
                user.getUserName()
        );

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "정보 조회 성공");
        result.put("data", res);

        return ResponseEntity.ok().body(result);
    }


    @PutMapping("/api/user/me")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody UserInfoReqDTO userInfoReqDTO) {
        int userId = SecurityUtil.getCurrentUserIdAsInt();

        User user = userService.updatedUser(userInfoReqDTO,userId);

        UserInfoResDTO res = new UserInfoResDTO(
                user.getProfile_url(),
                user.getUpdated_at(),
                user.getCreatedAt(),
                user.getCollection_id(),
                user.getEmail(),
                user.getUserName()
        );

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "유저 정보 변경 성공");
        result.put("data", res);

        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/api/user/me/key")
    public ResponseEntity<Map<String, Object>> getUserOpenAIKey() {
        int userId = SecurityUtil.getCurrentUserIdAsInt();

        User user = userService.getUser(userId);
        String MaskingKey = MaskingUtil.maskAfter(user.getOpen_key(), 5);
        UserOpenAIKeyResDTO res = new UserOpenAIKeyResDTO( MaskingKey );

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "정보 조회 성공");
        result.put("data", res);

        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/api/user/me/key")
    public ResponseEntity<Map<String, Object>> setUserOpenAIKey(@RequestBody UserOpenAIKeyReqDTO dto) {
        int userId = SecurityUtil.getCurrentUserIdAsInt();

        User user = userService.setUserOpenAiKey(userId, dto);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "OpenAi API key 등록 성공");

        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping ("/api/user/me/key")
    public ResponseEntity<Map<String, Object>> deleteUserOpenAIKey() {
        int userId = SecurityUtil.getCurrentUserIdAsInt();

        User user = userService.setUserOpenAiKey(userId, null);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "OpenAi API key 삭제 성공");

        return ResponseEntity.ok().body(result);
    }
}