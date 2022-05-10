package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.global.dto.DtoMetaData;

@Data
public class ProjectUpdateRequestDto {
    private Long projectId;
    private String projectName;
    private Long userId;
    private int headcount;
    private String field;
    private String habitat;
}
