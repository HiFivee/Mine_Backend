package org.hifivee.minebackend.domain.team.dto;

import lombok.Data;

@Data
public class TeamFetchRequestDto {
    private Long teamId;
    private Integer pageIndex;
    private Long topId;
}
