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
    private String user_id;
    private int headcount;
    private String field;
    private String habitat;

    public Project toProject(){
        return Project.builder()
                .project_name(project_name)
                .user_id(user_id)
                .headcount(headcount)
                .field(field)
                .habitat(habitat)
                .build();
    }
}
