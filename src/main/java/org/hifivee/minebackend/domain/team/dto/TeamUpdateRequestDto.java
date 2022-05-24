package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;

@Data
public class TeamUpdateRequestDto {
    private Long teamId;
    private Long projectId;
    private String teamName;
    private String projectName;
    private int teamNumber;
}
