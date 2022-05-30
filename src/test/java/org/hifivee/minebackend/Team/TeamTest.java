package org.hifivee.minebackend.Team;

import org.assertj.core.api.Assertions;
import org.hifivee.minebackend.domain.team.repository.Team;
import org.hifivee.minebackend.domain.team.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeamTest {

    @Autowired
    TeamRepository teamRepository;

    @Test
    void save(){
        Team teams = Team.builder()
                .teamNumber(12)
                .projectId(Long.valueOf("1"))
                .projectName("mineProject")
                .teamName("mine")
                .userId(Long.valueOf("11"))
                .build();


        teamRepository.save(teams);

        Team entity = teamRepository.findById((long)1).get();
        Assertions.assertThat(entity.getTeamName()).isEqualTo("mine");

    }

}
