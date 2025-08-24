package com.born.artify.auth.controller;

import com.born.artify.auth.dto.EmailReqDTO;
import com.born.artify.auth.dto.LoginReqDTO;
import com.born.artify.auth.dto.TokenResDTO;
import com.born.artify.auth.service.AuthService;

import com.born.artify.config.JwtProvider;
import com.born.artify.domain.user.dto.CreateUserDTO;
import com.born.artify.domain.user.entity.User;
import com.born.artify.domain.user.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


@Controller
public class AuthController {

    private final JwtProvider jwtTokenProvider;
    private final AuthService authService;
    private final UserService userService;


    public AuthController(JwtProvider jwtTokenProvider, AuthService authService, UserService userService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.authService = authService;
        this.userService = userService;
    }



    @PostMapping("/api/auth/signup")
    public ResponseEntity<Map<String, Object>> signUp(@Valid @RequestBody CreateUserDTO request) {
        System.out.println(request);
        User newUser = userService.createUser(request);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "성공적으로 가입되었습니다.");

        return ResponseEntity.ok().body(result);
    }


    @PostMapping("/api/auth/login")
    public ResponseEntity<Map<String, Object>>login(@RequestBody LoginReqDTO request) {
        TokenResDTO token = authService.login(request);

        Map<String, Object> result = new HashMap<>();
        result.put("status", 200);
        result.put("msg", "성공적으로 로그인이되었습니다.");
        result.put("accessToken", token.getAccessToken());
        result.put("refreshToken", token.getRefreshToken());

        return ResponseEntity.ok()
                .header("Authorization", "Bearer " + token.getAccessToken())
                .header("Refresh-Token", token.getRefreshToken())
                .body(result);
    }


    @PostMapping("/api/auth/email/check")
    public ResponseEntity<Map<String, Object>>existCheckEmail(@RequestBody EmailReqDTO request) {
        boolean isExistEmail = authService.existEmailCheck(request);

        Map<String, Object> result = new HashMap<>();

        if(isExistEmail) {
            result.put("status", 401);
            result.put("msg", "이미 사용중인 이메일입니다.");
        }else{
            result.put("status", 200);
            result.put("msg", "이메일을 사용하실 수 있습니다.");
        }

        return ResponseEntity.ok().body(result);
    }


   /* @PostMapping("/logout")
    public ResponseEntity<Void> logout(
            @RequestHeader("Authorization") String accessToken,
            HttpServletResponse response,
            @CookieValue(name = "refreshToken", required = false) String refreshToken
    ) {
        authService.logout(accessToken, refreshToken);

        // 쿠키 삭제
        Cookie cookie = new Cookie("refreshToken", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // HTTPS 환경에서만 전송
        cookie.setPath("/");
        cookie.setMaxAge(0); // 즉시 만료
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }*/


    @GetMapping("/main")
    public ResponseEntity<Map<String, Object>>mainTest() throws IOException {

       Map<String, Object> result = new HashMap<>();

       result.put("status", 200);
       result.put("msg", "성공적으로 가입되었습니다.");

       return ResponseEntity.ok().body(result);
    }

}
