package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProjectCreateRequestDto {

    private String project_name;
    private String userid;
    private int headcount;
    private String field;
    private String habitat;

    public Project toProject(){
        return Project.builder()
                .project_name(project_name)
                .userid(userid)
                .headcount(headcount)
                .field(field)
                .habitat(habitat)
                .build();
    }
}
