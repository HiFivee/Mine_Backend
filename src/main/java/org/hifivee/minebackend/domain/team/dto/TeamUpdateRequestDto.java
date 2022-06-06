package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;

@Data
public class TeamUpdateRequestDto {
    private Long teamId;
    private Project project;
    private String teamName;
    private String projectName;
    private int teamNumber;
}
