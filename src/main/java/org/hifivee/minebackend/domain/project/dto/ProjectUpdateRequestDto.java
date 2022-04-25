package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
public class ProjectUpdateRequestDto {
    private DtoMetaData dtoMetaData;
    private Long project_id;
    private String project_name;
    private String user_id;
    private int headcount;
    private String field;
    private String habitat;
}
