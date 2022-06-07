package org.hifivee.minebackend.domain.recruit.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.entity.BaseEntity;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(targetEntity = Project.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "projectId", referencedColumnName = "projectId")
    private Project project;

    @Column
    private String description;

    @Column
    private String attachments;

    @Column
    private Integer openings;

    @Column
    private String applicants;

    @Builder
    public Recruit(Project project, String description, String attachments, Integer openings, String applicants) {
        this.project = project;
        this.description = description;
        this.attachments = attachments;
        this.openings = openings;
        this.applicants = applicants;
    }

    public void update(Project project, String description, String attachments, Integer openings, String applicants) {
        this.project = project;
        this.description = description;
        this.attachments = attachments;
        this.openings = openings;
        this.applicants = applicants;
    }
}
