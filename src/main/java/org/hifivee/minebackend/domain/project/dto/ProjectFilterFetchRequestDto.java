package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;

@Data
public class ProjectFilterFetchRequestDto {
    private String projectName;
    private int headcount;
    private String field;
    private String habitat;
}
