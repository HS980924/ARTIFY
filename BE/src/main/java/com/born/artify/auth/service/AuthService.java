package com.born.artify.auth.service;

import com.born.artify.auth.dto.EmailReqDTO;
import com.born.artify.auth.dto.LoginReqDTO;
import com.born.artify.auth.dto.TokenResDTO;
import com.born.artify.config.JwtProvider;
import com.born.artify.domain.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.born.artify.domain.user.entity.User;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final JwtProvider jwtProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, JwtProvider jwtProvider, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
        this.passwordEncoder = passwordEncoder;
    }

    public TokenResDTO login(LoginReqDTO req) {
        User user = userRepository.findByEmail(req.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다."));

        if (!passwordEncoder.matches(req.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 올바르지 않습니다.");
        }

        String accessToken = jwtProvider.createAccessToken(user.getId().toString());
        String refreshToken = jwtProvider.createRefreshToken(user.getId().toString());

        return new TokenResDTO(accessToken, refreshToken);
    }

    public void logout(String accessToken) {
        //String token = accessToken.replace("Bearer ", "");

        // 액세스 토큰에서 사용자 ID 추출lo
        //String userId = jwtProvider.extractUserId(token);

        // 저장된 RefreshToken 제거
        // refreshTokenRepository.deleteByUserId(userId);

        // 필요 시 AccessToken을 블랙리스트에 등록할 수도 있음 (Redis 등 사용 시)
    }

    public boolean existEmailCheck(EmailReqDTO req){
        boolean isExistEmail = userRepository.existsByEmail(req.getEmail());

        return isExistEmail;
    }

}