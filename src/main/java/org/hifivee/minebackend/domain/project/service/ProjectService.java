package org.hifivee.minebackend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.project.dto.*;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.project.repository.ProjectRepository;
import org.hifivee.minebackend.domain.project.repository.ProjectSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
   // private final ProjectSpecification projectSpecification;
    // 프로젝트 생성
    @Transactional
    public void createProject(ProjectCreateRequestDto requestDto){
        projectRepository.save(requestDto.toProject());
    }

    // 프로젝트 조회
    @Transactional
    public Project fetchProject(ProjectFetchRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProject_id()).orElseThrow(() ->
                new IllegalArgumentException("해당 프로젝트가 없습니다."));
        return project;
    }

    // 프로젝트 전체 조회
    @Transactional
    public List<Project> fetchAllProject() {
        List<Project> projects = projectRepository.findAll();
        return projects;
    }

    // 유저의 프로젝트 조회
    @Transactional
    public List<Project> fetchMyProject(ProjectMyFetchRequestDto requestDto) {
        List<Project> projects = projectRepository.findByUserid(requestDto.getUserid());

        if(projects.isEmpty()){
            return null;
        }
        else{
            return projects;
        }
    }

    // 프로젝트 이름을 통한 검색 조회
    @Transactional
    public List<Project> fetchFilterProject(ProjectFilterFetchRequestDto requestDto) {
        Specification<Project> spec = (root, query, criteriaBuilder) -> null;

        if(requestDto.getProject_name() != null){
            spec = spec.and(ProjectSpecification.containingProject_name(requestDto.getProject_name()));
        }
        if(requestDto.getHeadcount() != 0){
            spec = spec.and(ProjectSpecification.lessThanHeadcount(requestDto.getHeadcount()));
        }
        if(requestDto.getField() != null){
            spec = spec.and(ProjectSpecification.equalField(requestDto.getField()));
        }
        if(requestDto.getHabitat() != null){
            spec = spec.and(ProjectSpecification.equalHabitat(requestDto.getHabitat()));
        }

        List<Project> project = projectRepository.findAll(spec);

        if(project.isEmpty()){
            return null;
        }
        else{
            return project;
        }
    }

    // 프로젝트 업데이트
    @Transactional
    public void updateProject(ProjectUpdateRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProject_id()).orElseThrow(() ->
                new IllegalArgumentException("해당 프로젝트가 없습니다."));

        project.update(requestDto.getProject_name(), requestDto.getHeadcount(), requestDto.getField(), requestDto.getHabitat());
    }

    // 프로젝트 삭제
    @Transactional
    public void deleteProejct(ProjectDeleteRequestDto requestDto){
        Project project = projectRepository.findById(requestDto.getProject_id()).orElseThrow(() ->
                new IllegalArgumentException("삭제할 프로젝트가 없습니다."));

        projectRepository.delete(project);
    }

}
