package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.*;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.service.AccountService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/account")
@RestController
public class AccountController {

    private final AccountService accountService;

    // 계정 생성
    @PostMapping
    public ResponseEntity<AccountCreateResponseDto> createAccount(@RequestBody AccountCreateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            accountService.createAccount(requestDto);
            dtoMetaData = new DtoMetaData("계정 생성 성공");
            return ResponseEntity.ok(new AccountCreateResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountCreateResponseDto(dtoMetaData));
        }
    }

    // 계정 가져오기: 모든 사용자 계정 정보를 가져올 수 있음
    @GetMapping
    public ResponseEntity<AccountFetchResponseDto> fetchAccount(@RequestBody AccountFetchRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            Account account = accountService.fetchAccount(requestDto);
            dtoMetaData = new DtoMetaData("계정 정보 가져오기 성공");
            return ResponseEntity.ok(new AccountFetchResponseDto(dtoMetaData, account));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountFetchResponseDto(dtoMetaData));
        }
    }

    // 계정 업데이트: 사용자 자신의 계정만 업데이트 가능
    @PutMapping
    public ResponseEntity<AccountUpdateResponseDto> updateAccount(@RequestBody AccountUpdateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            accountService.updateAccount(requestDto);
            dtoMetaData = new DtoMetaData("계정 업데이트 성공");
            return ResponseEntity.ok(new AccountUpdateResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountUpdateResponseDto(dtoMetaData));
        }
    }

    // 계정 삭제: 사용자 자신의 계정만 삭제 가능
    @DeleteMapping
    public ResponseEntity<AccountDeleteResponseDto> deleteAccount() {
        DtoMetaData dtoMetaData;

        try {
            accountService.deleteAccount();
            dtoMetaData = new DtoMetaData("계정 삭제 성공");
            return ResponseEntity.ok(new AccountDeleteResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AccountDeleteResponseDto(dtoMetaData));
        }
    }
}
