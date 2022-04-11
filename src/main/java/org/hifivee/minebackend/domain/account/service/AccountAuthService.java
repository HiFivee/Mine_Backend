package org.hifivee.minebackend.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountRequestDto;
import org.hifivee.minebackend.domain.account.dto.AccountResponseDto;
import org.hifivee.minebackend.domain.account.dto.AccountTokenDto;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.global.jwt.JwtTokenProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountAuthService {

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider tokenProvider;

    @Transactional
    public AccountResponseDto signup(AccountRequestDto requestDto) {
        if(accountRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다");
        }

        Account account = accountRepository.save(requestDto.toAccount(passwordEncoder));
        return new AccountResponseDto(account);
    }

    @Transactional
    public AccountTokenDto login(AccountRequestDto requestDto) {
        // Login ID/PW 를 기반으로 AuthenticationToken 생성
        UsernamePasswordAuthenticationToken authenticationToken = requestDto.toAuthentication();

        // AuthenticationToken (유저 정보: 비밀번호) 검증
        // authenticate 메서드가 실행이 될 때 CustomUserDetailsService 에서 만들었던 loadUserByUsername 메서드가 실행됨
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        // 인증 정보를 기반으로 JWT 토큰 생성
        String token = tokenProvider.generateToken(authentication);

        // 토큰 발급
        return new AccountTokenDto(token);
    }
}
