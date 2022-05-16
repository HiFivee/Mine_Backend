package org.hifivee.minebackend.domain.devArea.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hifivee.minebackend.domain.devArea.repository.DevArea;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Setter
@NoArgsConstructor
@Data
public class DevAreaFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private String techStack;

    public DevAreaFetchResponseDto(DtoMetaData dtoMetaData, DevArea devArea) {
        this.dtoMetaData = dtoMetaData;
        this.techStack = devArea.getTechStack();
    }

    public DevAreaFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.techStack = null;
    }
}
