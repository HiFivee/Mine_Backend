package org.hifivee.minebackend.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import javax.persistence.Column;

@Data
public class ProjectFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private String getProjectName;
    private Account account;
    private int headcount;
    private String field;
    private String habitat;

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData, Project project){
        this.dtoMetaData = dtoMetaData;
        this.getProjectName = project.getProjectName();
        this.account = project.getAccount();
        this.headcount = project.getHeadcount();
        this.field = project.getField();
        this.habitat = project.getHabitat();
    }

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.getProjectName = null;
        this.account = null;
        this.headcount = 0;
        this.field = null;
        this.habitat = null;
    }
}
