package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.team.repository.Team;

@Data
@NoArgsConstructor
public class TeamCreateRequestDto {
    private String teamName;
    private Long userId;
    private Long projectId;
    private String projectName;
    private int teamNumber;

    public Team toTeam(Account account){
        return Team.builder()
                .teamName(teamName)
                .projectId(projectId)
                .userId(userId)
                .projectName(projectName)
                .teamNumber(teamNumber)
                .build();
    }
}
