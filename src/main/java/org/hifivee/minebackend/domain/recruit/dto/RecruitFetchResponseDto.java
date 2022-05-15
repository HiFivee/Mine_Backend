package org.hifivee.minebackend.domain.recruit.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Setter
@NoArgsConstructor
@Data
public class RecruitFetchResponseDto {

    private DtoMetaData dtoMetaData;
    private List<Recruit> recruitList;
    private Pageable pageable;
    private Boolean isLast;

    public RecruitFetchResponseDto(List<Recruit> recruitList) {
        this.dtoMetaData = null;
        this.recruitList = recruitList;
        this.pageable = null;
        this.isLast = null;
    }

    public RecruitFetchResponseDto(List<Recruit> recruitList, Pageable pageable, Boolean isLast) {
        this.dtoMetaData = null;
        this.recruitList = recruitList;
        this.pageable = pageable;
        this.isLast = isLast;
    }

    public RecruitFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.recruitList = null;
        this.pageable = null;
        this.isLast = null;
    }
}
