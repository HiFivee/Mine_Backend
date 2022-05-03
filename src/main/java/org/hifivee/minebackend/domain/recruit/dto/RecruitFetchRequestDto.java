package org.hifivee.minebackend.domain.recruit.dto;

import lombok.Data;

@Data
public class RecruitFetchRequestDto {

    private Long id;
    private Integer pageIndex;
    private Long topId;
}
