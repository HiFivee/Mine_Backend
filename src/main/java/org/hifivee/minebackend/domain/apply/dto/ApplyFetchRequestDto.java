package org.hifivee.minebackend.domain.apply.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;

@Data
public class ApplyFetchRequestDto {
    // 특정 지원서
    //private long id;

    // 나의 지원서
    //private Long userId;

    // 구인글별 지원서
    //private Long recruitId;

    private long id;
    private Long userId;
    private Long recruitId;
}
