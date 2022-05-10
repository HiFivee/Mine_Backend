package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.project.repository.Project;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
public class ProjectCreateRequestDto {

    private String projectName;
    private Long userId;
    private int headcount;
    private String field;
    private String habitat;

    public Project toProject(Account account){
        return Project.builder()
                .projectName(projectName)
                .account(account)
                .headcount(headcount)
                .field(field)
                .habitat(habitat)
                .build();
    }
}
