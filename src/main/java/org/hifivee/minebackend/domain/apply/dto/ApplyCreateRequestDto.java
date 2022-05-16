package org.hifivee.minebackend.domain.apply.dto;

import lombok.Data;

@Data
public class ApplyCreateRequestDto {
    private Long userId;
    private String applyMessage;
    private Long recruitId;
}
