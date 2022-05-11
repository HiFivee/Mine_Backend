package org.hifivee.minebackend.domain.project.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.project.dto.*;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.project.service.ProjectService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/project")
@RestController
public class ProjectController {

    private final ProjectService projectService;

    // 프로젝트 생성
    @PostMapping
    public ResponseEntity<ProjectCreateResponseDto> createProject(@RequestBody ProjectCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;
        try{
            projectService.createProject(requestDto);
            dtoMetaData = new DtoMetaData("프로젝트 생성 완료");
            return ResponseEntity.ok(new ProjectCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectCreateResponseDto(dtoMetaData));
        }
    }

    // 프로젝트 조회
    @GetMapping
    public ResponseEntity<ProjectFetchResponseDto> fetchProject(@RequestBody ProjectFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;
        List<Project> projects;
        try{
            if(requestDto.getProjectId() != null){
                projects = projectService.fetchProject(requestDto);
                dtoMetaData = new DtoMetaData("특정 프로젝트 조회 완료");
            }
            else if(requestDto.getUserId() != null){
                projects = projectService.fetchMyProject(requestDto);
                dtoMetaData = new DtoMetaData("내 프로젝트 조회 완료");
            }
            else if(requestDto.getProjectName() != null){
                projects = projectService.fetchFilterProject(requestDto);
                dtoMetaData = new DtoMetaData("검색한 프로젝트 조회 완료");
            }
            else {
                projects = projectService.fetchAllProject();
                dtoMetaData = new DtoMetaData("전체 프로젝트 조회 완료");
            }
            return ResponseEntity.ok(new ProjectFetchResponseDto(dtoMetaData, projects));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectFetchResponseDto(dtoMetaData));
        }
    }

    // 프로젝트 업데이트
    @PutMapping
    public ResponseEntity<ProjectUpdateResponseDto> updateProject(@RequestBody ProjectUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            projectService.updateProject(requestDto);
            dtoMetaData = new DtoMetaData("프로젝트 정보 갱신 완료");
            return ResponseEntity.ok(new ProjectUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectUpdateResponseDto(dtoMetaData));
        }
    }
    // 프로젝트 삭제
    @DeleteMapping
    public ResponseEntity<ProjectDeleteResponseDto> deleteProject(@RequestBody ProjectDeleteRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            projectService.deleteProejct(requestDto);
            dtoMetaData = new DtoMetaData("프로젝트 삭제 완료");
            return ResponseEntity.ok(new ProjectDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectDeleteResponseDto(dtoMetaData));
        }
    }
}

