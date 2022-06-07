package org.hifivee.minebackend.domain.recruit.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;

@Data
public class RecruitCreateRequestDto {

    private Long projectId;
    private String description;
    private String attachments;
    private Integer openings;
    private String applicants;

    public Recruit toEntity(Project project) {
        return Recruit.builder()
                .project(project)
                .description(description)
                .attachments(attachments)
                .openings(openings)
                .applicants(applicants)
                .build();
    }
}
