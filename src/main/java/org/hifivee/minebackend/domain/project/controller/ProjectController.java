package org.hifivee.minebackend.domain.project.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.project.dto.*;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.project.service.ProjectService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

        try{
            Project project = projectService.fetchProject(requestDto);
            dtoMetaData = new DtoMetaData("프로젝트 조회 완료");
            return ResponseEntity.ok(new ProjectFetchResponseDto(dtoMetaData, project));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectFetchResponseDto(dtoMetaData));
        }
    }

    // 프로젝트 조회(전체)
    @GetMapping("/all")
    public ResponseEntity<ProjectAllFetchResponseDto> fetchAllProject(){
        DtoMetaData dtoMetaData;

        try{
            List<Project> projects = projectService.fetchAllProject();
            dtoMetaData = new DtoMetaData("프로젝트 전체 조회 완료");
            return ResponseEntity.ok(new ProjectAllFetchResponseDto(dtoMetaData, projects));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectAllFetchResponseDto(dtoMetaData));
        }
    }

    // 프로젝트 조회(유저)
    @GetMapping("/myproject")
    public ResponseEntity<ProjectMyFetchResponseDto> fetchMyProject(@RequestBody ProjectMyFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            List<Project> projects = projectService.fetchMyProject(requestDto);
            if(projects == null){
                dtoMetaData = new DtoMetaData("나의 프로젝트가 없습니다.");
            }
            else {
                dtoMetaData = new DtoMetaData("내 프로젝트 조회 완료");
            }
            return ResponseEntity.ok(new ProjectMyFetchResponseDto(dtoMetaData, projects));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectMyFetchResponseDto(dtoMetaData));
        }
    }

    // 프로젝트 조회(이름 검색)
    @GetMapping("/filterproject")
    public ResponseEntity<ProjectFilterFetchResponseDto> fetchFilterProject(@RequestBody ProjectFilterFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;

        try{
            List<Project> projects = projectService.fetchFilterProject(requestDto);
            if(projects == null){
                dtoMetaData = new DtoMetaData("조건에 맞는 프로젝트가 없습니다.");
            }
            else {
                dtoMetaData = new DtoMetaData("프로젝트 검색 완료");
            }
            return ResponseEntity.ok(new ProjectFilterFetchResponseDto(dtoMetaData, projects));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ProjectFilterFetchResponseDto(dtoMetaData));
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
