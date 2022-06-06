package org.hifivee.minebackend.domain.team.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.entity.BaseEntity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Team extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long teamId;

    @Column
    private String teamName;

    @Column
    private int teamNumber;

    @OneToOne
    @JoinColumn(name = "PROJECT_ID")
    private Project project;

    @Column
    private String projectName;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "team")
    private final List<Account> members = new ArrayList<>();

    @Builder
    public Team(String teamName, int teamNumber, Project projectId, String projectName) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
        this.project = projectId;
        this.projectName = projectName;
    }

    public void update(String teamName, int teamNumber) {
        this.teamName = teamName;
        this.teamNumber = teamNumber;
    }

    public void putMember(Account account){
        this.members.add(account);
    }
}
