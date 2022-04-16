package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;

@Data
public class AccountPasswordUpdateRequestDto {

    private String password;
    private String newPassword;
}
