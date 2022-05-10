package org.hifivee.minebackend.domain.project.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.domain.project.dto.*;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.hifivee.minebackend.domain.project.repository.ProjectRepository;
import org.hifivee.minebackend.domain.project.repository.ProjectSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final AccountRepository accountRepository;

    // 프로젝트 생성
    @Transactional
    public void createProject(ProjectCreateRequestDto requestDto){
        Account user = accountRepository.findById(requestDto.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("유저 정보가 없습니다"));

        projectRepository.save(requestDto.toProject(user));

    }

    // 프로젝트 조회
    @Transactional
    public Project fetchProject(ProjectFetchRequestDto requestDto) {
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() ->
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
        Account user = accountRepository.findById(requestDto.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("유저 정보가 없습니다"));
        List<Project> projects = projectRepository.findByUserId(user);

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

        if(requestDto.getProjectName() != null){
            spec = spec.and(ProjectSpecification.containingProject_name(requestDto.getProjectName()));
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
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() ->
                new IllegalArgumentException("해당 프로젝트가 없습니다."));

        project.update(requestDto.getProjectName(), requestDto.getHeadcount(), requestDto.getField(), requestDto.getHabitat());
    }

    // 프로젝트 삭제
    @Transactional
    public void deleteProejct(ProjectDeleteRequestDto requestDto){
        Project project = projectRepository.findById(requestDto.getProjectId()).orElseThrow(() ->
                new IllegalArgumentException("삭제할 프로젝트가 없습니다."));

        projectRepository.delete(project);
    }

}
