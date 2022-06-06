package org.hifivee.minebackend.Team;

import org.assertj.core.api.Assertions;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.team.repository.Team;
import org.hifivee.minebackend.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
public class TeamTest {

    @Autowired
    TeamRepository teamRepository;



    @Test
    void save(){
        Team teams = Team.builder()
                .teamNumber(12)
                .projectId(new Project())
                .projectName("mineProject")
                .teamName("mine")
                .build();


        teamRepository.save(teams);

        Team entity = teamRepository.findById((long)1).get();
        Assertions.assertThat(entity.getTeamName()).isEqualTo("mine");

    }

}
