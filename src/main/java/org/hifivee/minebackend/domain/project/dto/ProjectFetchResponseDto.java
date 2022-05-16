package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import java.util.List;

@Data
public class ProjectFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Project> projects;

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData, List<Project> project){
        this.dtoMetaData = dtoMetaData;
        this.projects = project;
    }

    public ProjectFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
    }
}
