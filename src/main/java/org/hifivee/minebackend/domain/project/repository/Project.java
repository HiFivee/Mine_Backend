package org.hifivee.minebackend.domain.project.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.team.repository.Team;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
public class Project implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long projectId;

    @Column
    private String projectName;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;

    @Column
    private int headcount;

    @Column
    private String field;

    @Column
    private String habitat;

    @OneToOne(mappedBy = "project")
    private Team team;


    @Builder
    public Project(String projectName, Account account, int headcount, String field, String habitat, Team team){
        this.projectName = projectName;
        this.account = account;
        this.headcount = headcount;
        this.field = field;
        this.habitat = habitat;
        this.team = team;
    }

    public void update(String projectName, int headcount, String field, String habitat){
        this.projectName = projectName;
        this.headcount = headcount;
        this.field = field;
        this.habitat = habitat;
    }
}
