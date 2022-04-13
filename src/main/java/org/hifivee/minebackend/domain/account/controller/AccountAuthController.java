package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountLoginRequestDto;
import org.hifivee.minebackend.domain.account.dto.AccountLoginResponseDto;
import org.hifivee.minebackend.domain.account.service.AccountAuthService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
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

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<AccountLoginResponseDto> login(@RequestBody AccountLoginRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            String token = accountAuthService.login(requestDto);
            dtoMetaData = new DtoMetaData("로그인 성공");
            return ResponseEntity.ok(new AccountLoginResponseDto(dtoMetaData, token));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountLoginResponseDto(dtoMetaData));
        }
    }
}
