package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
public class AccountLoginResponseDto {

    private DtoMetaData dtoMetaData;
    private String token;

    public AccountLoginResponseDto(DtoMetaData dtoMetaData, String token) {
        this.dtoMetaData = dtoMetaData;
        this.token = token;
    }

    public AccountLoginResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.token = null;
    }
}
