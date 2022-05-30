package org.hifivee.minebackend.domain.team.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.global.entity.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Team extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private String teamName;

    @Column
    private int teamNumber;

    @Column
    private Long projectId;

    @Column
    private String projectName;

    @Column
    private Long userId;

    @Builder
    public Team(String teamName, int teamNumber, Long projectId, String projectName, Long userId) {
        this.teamName = teamName;
        this.userId = userId;
        this.teamNumber = teamNumber;
        this.projectId = projectId;
        this.projectName = projectName;
    }

    public void update(String teamName, int teamNumber) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
    }
}
