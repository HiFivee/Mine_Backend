package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;

@Data
public class ProjectFilterFetchRequestDto {
    private String project_name;
    private int headcount;
    private String field;
    private String habitat;
}
