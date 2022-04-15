package org.hifivee.minebackend.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AuthCodeSendRequestDto;
import org.hifivee.minebackend.domain.account.dto.AuthCodeVerifyRequestDto;
import org.hifivee.minebackend.domain.account.dto.LoginRequestDto;
import org.hifivee.minebackend.global.jwt.JwtTokenProvider;
import org.hifivee.minebackend.global.mail.MailService;
import org.hifivee.minebackend.global.security.AuthCodeService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider tokenProvider;
    private final AuthCodeService authCodeService;
    private final MailService mailService;

    @Transactional
    public String login(LoginRequestDto requestDto) {
        // Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        // AuthenticationToken (유저 정보: 비밀번호) 검증
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        String token = tokenProvider.generateToken(authentication);

        // 토큰 발급
        return token;
    }

    public void sendAuthCode(AuthCodeSendRequestDto requestDto) throws Exception {
        // 인증 코드 메일 보내기
        mailService.sendAuthCodeMail(requestDto.getEmail());
    }

    public void verifyAuthCode(AuthCodeVerifyRequestDto requestDto) {
        // 인증 코드가 유효하면
        if(authCodeService.validateAuthCode(requestDto.getEmail(), requestDto.getAuthCode())) {
            // NO ACTION
        }
        // 인증 코드가 유효하지 않으면
        else {
            throw new IllegalArgumentException("인증 코드가 유효하지 않습니다.");
        }
    }
}
