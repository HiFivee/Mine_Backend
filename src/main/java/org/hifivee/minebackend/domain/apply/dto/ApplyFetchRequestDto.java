package org.hifivee.minebackend.domain.apply.dto;

import lombok.Data;

@Data
public class ApplyFetchRequestDto {
    // 특정 지원서
    //private Long id;

    // 나의 지원서
    //private Long userId;

    // 구인글별 지원서
    //private Long recruitId;

    private Long id;
    private Long userId;
    private Long recruitId;
}
