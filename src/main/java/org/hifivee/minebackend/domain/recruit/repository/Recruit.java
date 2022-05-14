package org.hifivee.minebackend.domain.recruit.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.global.entity.BaseEntity;

import javax.persistence.*;


@Getter
@NoArgsConstructor
@Entity
public class Recruit extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long projectId;
    private String description;
    private String attachments;
    private Integer openings;
    private String applicants;

    @Builder
    public Recruit(Long projectId, String description, String attachments, Integer openings, String applicants) {
        this.projectId = projectId;
        this.description = description;
        this.attachments = attachments;
        this.openings = openings;
        this.applicants = applicants;
    }

    public void update(Long projectId, String description, String attachments, Integer openings, String applicants) {
        this.projectId = projectId;
        this.description = description;
        this.attachments = attachments;
        this.openings = openings;
        this.applicants = applicants;
    }
}
