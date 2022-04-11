package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;

@Data
public class AccountTokenDto {

    private String token;

    public AccountTokenDto(String token) {
        this.token = token;
    }
}
