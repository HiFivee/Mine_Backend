package org.hifivee.minebackend.domain.account.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
@AllArgsConstructor
public class AuthCodeVerifyResponseDto {

    private DtoMetaData dtoMetaData;
}