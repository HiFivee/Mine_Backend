package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import java.util.List;

@Data
public class ProjectMyFetchResponseDto {
    private DtoMetaData dtoMetaData;
    private List<Project> projects;

    public ProjectMyFetchResponseDto(DtoMetaData dtoMetaData, List<Project> projects) {
        this.dtoMetaData = dtoMetaData;
        this.projects = projects;
    }

    public ProjectMyFetchResponseDto(DtoMetaData dtoMetaData) {
        this.dtoMetaData = dtoMetaData;
        this.projects = null;
    }
}
