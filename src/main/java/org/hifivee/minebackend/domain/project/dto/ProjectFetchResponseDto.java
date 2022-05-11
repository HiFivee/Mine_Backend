package org.hifivee.minebackend.domain.project.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.global.dto.DtoMetaData;

import javax.persistence.Column;
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
