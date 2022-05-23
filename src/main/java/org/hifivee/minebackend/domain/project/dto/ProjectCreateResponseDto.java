package org.hifivee.minebackend.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import javax.persistence.Column;

@Data
@AllArgsConstructor
public class ProjectCreateResponseDto {
    private DtoMetaData dtoMetaData;
}
