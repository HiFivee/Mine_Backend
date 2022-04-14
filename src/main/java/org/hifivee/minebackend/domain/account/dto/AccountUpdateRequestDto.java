package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;

@Data
public class AccountUpdateRequestDto {

    private String nickname;
    private String phone;
    private String address;
    private String position;
    private String link;
    private String message;
    private String techStack;
    private String experience;
}
