package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.*;
import org.hifivee.minebackend.domain.account.service.AuthService;
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
public class AuthController {

    private final AuthService authService;

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            String token = authService.login(requestDto);
            dtoMetaData = new DtoMetaData("로그인 성공");
            return ResponseEntity.ok(new LoginResponseDto(dtoMetaData, token));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new LoginResponseDto(dtoMetaData));
        }
    }

    // 인증 코드 발송
    @PostMapping("/code/send")
    public ResponseEntity<AuthCodeSendResponseDto> sendAuthCode(@RequestBody AuthCodeSendRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            authService.sendAuthCode(requestDto);
            dtoMetaData = new DtoMetaData("인증 코드 전송 성공");
            return ResponseEntity.ok(new AuthCodeSendResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthCodeSendResponseDto(dtoMetaData));
        }
    }

    // 인증 코드 검증
    @PostMapping("/code/verify")
    public ResponseEntity<AuthCodeVerifyResponseDto> sendAuthCode(@RequestBody AuthCodeVerifyRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            authService.verifyAuthCode(requestDto);
            dtoMetaData = new DtoMetaData("인증 코드 검증 성공");
            return ResponseEntity.ok(new AuthCodeVerifyResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthCodeVerifyResponseDto(dtoMetaData));
        }
    }
}
