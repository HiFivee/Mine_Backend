package org.hifivee.minebackend.global.security;

import java.security.SecureRandom;
import java.util.Date;

public class AuthCodeGenerator {
    // 임시 코드 생성기
    public static String generate(char[] charSet, int length) {
        StringBuilder authCodeBuffer = new StringBuilder();
        SecureRandom secureRandomNumberGenerator = new SecureRandom();
        secureRandomNumberGenerator.setSeed(new Date().getTime());

        int randomCharIndex;
        for(int i=0; i<length; i++) {
            randomCharIndex = secureRandomNumberGenerator.nextInt(charSet.length);
            authCodeBuffer.append(charSet[randomCharIndex]);
        }

        return authCodeBuffer.toString();
    }
}
