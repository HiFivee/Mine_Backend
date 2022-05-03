package org.hifivee.minebackend.domain.recruit.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;

@Data
public class RecruitCreateRequestDto {

    private Long projectId;
    private String description;
    private String attachments;
    private Integer openings;
    private String applicants;

    public Recruit toEntity() {
        return Recruit.builder()
                .projectId(projectId)
                .description(description)
                .attachments(attachments)
                .openings(openings)
                .applicants(applicants)
                .build();
    }
}
