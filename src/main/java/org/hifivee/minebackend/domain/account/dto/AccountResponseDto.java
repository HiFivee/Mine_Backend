package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;

@Data
public class AccountResponseDto {

    private String email;

    public AccountResponseDto(Account account) {
        this.email = account.getEmail();
    }
}
