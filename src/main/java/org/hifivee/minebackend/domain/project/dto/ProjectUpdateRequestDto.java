package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;

@Data
public class ProjectUpdateRequestDto {
    private Long projectId;
    private String projectName;
    private Long userId;
    private int headcount;
    private String field;
    private String habitat;
}
