package org.hifivee.minebackend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.project.dto.*;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.project.repository.ProjectRepository;
import org.hifivee.minebackend.domain.project.repository.ProjectSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

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
        Optional<Project> projects = projectRepository.findByUser_id(requestDto.getUser_id());

        if(projects.isPresent()){ // 유저의 프로젝트가 존재하는 경우
            List<Project> my_projects = (List<Project>) projects.get();
            return my_projects;
        }
        else{ // 프로젝트가 없는 경우
            return null;
        }
    }

    // 프로젝트 이름을 통한 검색 조회
    @Transactional
    public List<Project> fetchFilterProject(ProjectFilterFetchRequestDto requestDto) {
        /*Optional<Project> project = projectRepository.findByProject_nameContaining(requestDto.getProject_name());

        if(project.isPresent()){ // 검색했을 때 프로젝트가 존재하는 경우
            List<Project> filter_projects = (List<Project>) project.get();
            return filter_projects;
        }
        else{ // 프로젝트가 없는 경우
            return null;
        }*/

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

        if(project.isEmpty()){ // 검색된 프로젝트가 없는 경우
            return null;
        }
        else{ // 검색된 프로젝트가 있는 경우
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
