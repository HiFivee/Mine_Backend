package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountRequestDto;
import org.hifivee.minebackend.domain.account.dto.AccountResponseDto;
import org.hifivee.minebackend.domain.account.dto.AccountTokenDto;
import org.hifivee.minebackend.domain.account.service.AccountAuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/auth")
@RestController
public class AccountAuthController {

    private final AccountAuthService accountAuthService;

    @PostMapping("/signup")
    public ResponseEntity<AccountResponseDto> signup(@RequestBody AccountRequestDto requestDto) {
        return ResponseEntity.ok(accountAuthService.signup(requestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<AccountTokenDto> login(@RequestBody AccountRequestDto requestDto) {
        return ResponseEntity.ok(accountAuthService.login(requestDto));
    }
}
