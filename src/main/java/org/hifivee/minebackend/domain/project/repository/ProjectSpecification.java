package org.hifivee.minebackend.domain.project.repository;

import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {
    // 프로젝트 이름에 검색어가 포함되어있는지 확인
    public static Specification<Project> containingProject_name(String project_name){
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("project_name"), "%"+project_name+"%");
    }
    // 프로젝트 인원 수가 검색한 인원 수보다 작거나 같은지 확인
    public static Specification<Project> lessThanHeadcount(Integer headcount){
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("headcount"), headcount);
    }
    // 모집분야가 같은지 확인
    public static Specification<Project> equalField(String field){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("field"), field);
    }
    // 거주지가 같은지 확인
    public static Specification<Project> equalHabitat(String habitat){
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("habitat"), habitat);
    }
}
