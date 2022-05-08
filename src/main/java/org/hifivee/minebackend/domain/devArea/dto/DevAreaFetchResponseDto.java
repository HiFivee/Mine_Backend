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
    private String userName;
    private String userTechStack;

    public DevAreaFetchResponseDto(DtoMetaData dtoMetaData, DevArea devArea) {
        this.dtoMetaData = dtoMetaData;
        this.userName = devArea.getUserName();
        this.userTechStack = devArea.getUserTechStack();
    }

    public DevAreaFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.userTechStack = null;
        this.userName = null;
    }
}
