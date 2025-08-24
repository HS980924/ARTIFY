package com.born.artify.util;

public class MaskingUtil {
    public static String maskAfter(String input, int length) {
        if (input == null || input.length() <= length) {
            return input; // 또는 전부 *로 바꾸고 싶다면: return "*".repeat(input.length());
        }

        String visiblePart = input.substring(0, length);
        String maskedPart = "*".repeat(input.length() - length);

        return visiblePart + maskedPart;
    }

}
