package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import java.util.List;

@Data
public class ProjectFilterFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Project> projects;

    public ProjectFilterFetchResponseDto(DtoMetaData dtoMetaData, List<Project> projects){
        this.dtoMetaData = dtoMetaData;
        this.projects = projects;
    }

    public ProjectFilterFetchResponseDto(DtoMetaData dtoMetaData){
        this.dtoMetaData = dtoMetaData;
    }
}
