package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;

@Data
public class TeamFetchRequestDto {
    private Long teamId;
    private String teamName;
    private Long projectId;
    private String projectName;
    private int teamNumber;
    private Integer pageIndex;
    private Long topId;
}
