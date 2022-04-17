package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;

@Data
public class AccountPasswordRecoverRequestDto {

    private String email;
    private String authCode;
}
