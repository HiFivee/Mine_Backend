package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import java.util.List;

@Data
public class ProjectAllFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Project> projects;

    public ProjectAllFetchResponseDto(DtoMetaData dtoMetaData, List<Project> projects) {
        this.dtoMetaData = dtoMetaData;
        this.projects = projects;
    }

    public ProjectAllFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.projects = null;
    }
}
