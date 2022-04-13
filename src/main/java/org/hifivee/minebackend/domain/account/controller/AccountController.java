package org.hifivee.minebackend.domain.account.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.AccountResponseDto;
import org.hifivee.minebackend.domain.account.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/myself")
    public ResponseEntity<AccountResponseDto> getMyAccountInfo() {
        return ResponseEntity.ok(accountService.getMyAccountInfo());
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<AccountResponseDto> getAccountInfo(@PathVariable String email) {
        return ResponseEntity.ok(accountService.getAccountInfo(email));
    }
}
