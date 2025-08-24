package com.born.artify.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtil {
    public static String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        return (String) authentication.getPrincipal();
    }

    // int 타입 userId 반환 (없거나 형식 안 맞으면 예외 발생 가능)
    public static int getCurrentUserIdAsInt() {
        String userIdStr = getCurrentUserId();
        if (userIdStr == null) {
            throw new IllegalStateException("인증된 사용자 ID가 없습니다.");
        }
        try {
            return Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            throw new IllegalStateException("userId가 숫자 형식이 아닙니다.", e);
        }
    }
}
