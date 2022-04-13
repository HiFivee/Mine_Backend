package org.hifivee.minebackend.domain.account.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.*;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.global.security.SecurityUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AccountService {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void createAccount(AccountCreateRequestDto requestDto) {
        // 이미 가입되어 있는지 email 을 기준으로 확인
        String email = requestDto.getEmail();
        if(accountRepository.existsByEmail(email)) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다. email: " + email);
        }

        // 계정 생성: 기본 정보 DB 에 저장 (패스워드는 인코딩하여 저장)
        accountRepository.save(requestDto.toAccount(passwordEncoder));
    }

    @Transactional
    public Account fetchAccount(AccountFetchRequestDto requestDto) {
        // id 가 null 이면: 요청을 보낸 사용자의 id 를 찾아 계정 정보 가져오기
        // id 가 null 이 아니면: requestDto 의 id 그대로 가져오기
        Long id = (requestDto.getId() == null) ? (SecurityUtil.getCurrentAccountId()) : (requestDto.getId());

        // 계정 id 를 받아 DB 에서 검색 후 반환
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다. id: " + id));

        return account;
    }

    @Transactional
    public void updateAccount(AccountUpdateRequestDto requestDto) {
        // 현재 요청을 보낸 사용자의 id 를 찾아 계정 정보 가져오기
        Long id = SecurityUtil.getCurrentAccountId();
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다. id: " + id));

        // 계정 정보 업데이트
        account.update(requestDto.getNickname(), requestDto.getPhone(), requestDto.getAddress(),
                requestDto.getPosition(), requestDto.getLink(), requestDto.getMessage(), requestDto.getTechStack(), requestDto.getExperience());
    }

    @Transactional
    public void deleteAccount() {
        // 현재 요청을 보낸 사용자의 id 를 찾아 계정 정보 가져오기
        Long id = SecurityUtil.getCurrentAccountId();
        Account account = accountRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다. id: " + id));

        // 계정 삭제
        accountRepository.delete(account);
    }
}
