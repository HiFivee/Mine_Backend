package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
public class AccountFetchResponseDto {

    private DtoMetaData dtoMetaData;
    private String email;
    private String nickname;
    private String phone;
    private String address;
    private String position;
    private String link;
    private String message;
    private String techStack;
    private String experience;

    public AccountFetchResponseDto(DtoMetaData dtoMetaData, Account account) {
        this.dtoMetaData = dtoMetaData;
        this.email = account.getEmail();
        this.nickname = account.getNickname();
        this.phone = account.getPhone();
        this.address = account.getAddress();
        this.position = account.getPosition();
        this.link = account.getLink();
        this.message = account.getMessage();
        this.techStack = account.getTechStack();
        this.experience = account.getExperience();
    }

    public AccountFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.email = null;
        this.nickname = null;
        this.phone = null;
        this.address = null;
        this.position = null;
        this.link = null;
        this.message = null;
        this.techStack = null;
        this.experience = null;
    }
}
