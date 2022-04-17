package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountPasswordRecoverRequestDto;
import org.hifivee.minebackend.domain.account.dto.AccountPasswordRecoverResponseDto;
import org.hifivee.minebackend.domain.account.service.AccountRecoveryService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api/recover")
@RestController
public class AccountRecoveryController {

    private final AccountRecoveryService accountRecoveryService;

    // 패스워드 복구
    @PostMapping("/password")
    public ResponseEntity<AccountPasswordRecoverResponseDto> recoverPassword(@RequestBody AccountPasswordRecoverRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            accountRecoveryService.recoverPassword(requestDto);
            dtoMetaData = new DtoMetaData("비밀번호 리셋 성공");
            return ResponseEntity.ok(new AccountPasswordRecoverResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountPasswordRecoverResponseDto(dtoMetaData));
        }
    }
}
