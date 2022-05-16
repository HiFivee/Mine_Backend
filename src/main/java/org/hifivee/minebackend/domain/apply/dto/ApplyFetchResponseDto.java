package org.hifivee.minebackend.domain.apply.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.domain.apply.repository.Apply;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import java.util.List;

@Data
public class ApplyFetchResponseDto {
    List<Apply> applies;
    DtoMetaData dtoMetaData;

    public ApplyFetchResponseDto(DtoMetaData dtoMetaData, List<Apply> applies){
        this.dtoMetaData = dtoMetaData;
        this.applies = applies;
    }

    public ApplyFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
