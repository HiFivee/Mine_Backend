package org.hifivee.minebackend.domain.team.dto;

import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.Data;
import org.hifivee.minebackend.domain.team.repository.Team;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Data
public class TeamFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Team> teams;
    private Pageable pageable;
    private Boolean isLast;


    public TeamFetchResponseDto(List<Team> teams, Pageable pageable, Boolean isLast) {
        this.dtoMetaData = null;
        this.isLast = isLast;
        this.pageable = pageable;
        this.teams = teams;
    }

    public TeamFetchResponseDto(List<Team> teams) {
        this.teams = teams;
        this.pageable = null;
        this.dtoMetaData = null;
    }

    public TeamFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.pageable = null;
        this.teams = null;
    }
}
