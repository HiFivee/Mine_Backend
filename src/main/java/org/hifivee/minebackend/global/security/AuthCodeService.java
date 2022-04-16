package org.hifivee.minebackend.global.security;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthCodeService {

    private final List<AuthCodeToken> verificationTokenList = new LinkedList<>();

    // 인증 코드 생성 (6자리)
    public String generateAuthCode(String source) {
        // 인증 코드 생성
        char[] authCodeCharSet = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        String authCode = TempCodeGenerator.generate(authCodeCharSet, 6);

        // verificationTokenList 에 이미 존재한다면 기존 인증 Token 제거
        verificationTokenList.removeIf((token) -> (token.getSource().equals(source)));

        // 만료시간 설정
        Calendar expiredTime = Calendar.getInstance();
        expiredTime.setTime(new Date());
        expiredTime.add(Calendar.MINUTE, 10);

        // verificationTokenList 에 저장
        verificationTokenList.add(new AuthCodeToken(source, authCode, expiredTime));

        return authCode;
    }

    // 인증 코드 검증
    public void validateAuthCode(String source, String authCode) throws Exception {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        for (AuthCodeToken token : verificationTokenList) {
            if(
                    token.getSource().equals(source) &&
                    token.getAuthCode().equals(authCode) &&
                    token.getExpireTime().compareTo(currentTime) > 0
            ) {
                verificationTokenList.remove(token);
                return;
            }
        }

        // 인증 코드가 맞지 않으면
        throw new IllegalArgumentException("인증 코드가 유효하지 않습니다.");
    }

    // 인증코드 리스트 정리: 만료가 된 인증코드 제거
    public void removeExpiredAuthCode() {
        Calendar currentTime = Calendar.getInstance();
        currentTime.setTime(new Date());

        verificationTokenList.removeIf((token) -> (token.getExpireTime().compareTo(currentTime) <= 0));
    }
}
