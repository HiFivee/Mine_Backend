package org.hifivee.minebackend.domain.account.dto;

import lombok.Data;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
public class LoginResponseDto {

    private DtoMetaData dtoMetaData;
    private String token;

    public LoginResponseDto(DtoMetaData dtoMetaData, String token) {
        this.dtoMetaData = dtoMetaData;
        this.token = token;
    }

    public LoginResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.token = null;
    }
}
