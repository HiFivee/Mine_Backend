package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;

@Data
public class AuthCodeVerifyRequestDto {

    private String email;
    private String authCode;
}
