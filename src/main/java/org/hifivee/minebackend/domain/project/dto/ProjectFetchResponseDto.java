package org.hifivee.minebackend.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import javax.persistence.Column;

@Data
public class ProjectFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private String project_name;
    private String userid;
    private int headcount;
    private String field;
    private String habitat;

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData, Project project){
        this.dtoMetaData = dtoMetaData;
        this.project_name = project.getProject_name();
        this.userid = project.getUserid();
        this.headcount = project.getHeadcount();
        this.field = project.getField();
        this.habitat = project.getHabitat();
    }

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.project_name = null;
        this.userid = null;
        this.headcount = 0;
        this.field = null;
        this.habitat = null;
    }
}
