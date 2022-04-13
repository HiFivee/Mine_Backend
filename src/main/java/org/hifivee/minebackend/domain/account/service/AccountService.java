package org.hifivee.minebackend.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountResponseDto;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.global.security.SecurityUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    // ADMIN 전용
    @Transactional(readOnly = true)
    public AccountResponseDto getAccountInfo(String email) {
        return new AccountResponseDto(accountRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다.")));
    }

    // USER 전용
    @Transactional(readOnly = true)
    public AccountResponseDto getMyAccountInfo() {
        return new AccountResponseDto(accountRepository.findById(SecurityUtil.getCurrentAccountId()).orElseThrow(() -> new IllegalArgumentException("현재 로그인된 유저 정보가 없습니다.")));
    }
}
