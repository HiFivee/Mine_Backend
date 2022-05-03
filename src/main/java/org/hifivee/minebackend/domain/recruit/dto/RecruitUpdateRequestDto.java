package org.hifivee.minebackend.domain.recruit.dto;

import lombok.Data;

@Data
public class RecruitUpdateRequestDto {

    private Long id;
    private Long projectId;
    private String description;
    private String attachments;
    private Integer openings;
    private String applicants;
}
