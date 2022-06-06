package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.team.repository.Team;

import java.util.List;

@Data
@NoArgsConstructor
public class TeamCreateRequestDto {
    private String teamName;
    private Project projectId;
    private String projectName;
    private int teamNumber;
    private List<Account> teamMember;

    public Team toTeam(){
        return Team.builder()
                .teamName(teamName)
                .projectId(projectId)
                .projectName(projectName)
                .teamNumber(teamNumber)
                .build();
    }
}
