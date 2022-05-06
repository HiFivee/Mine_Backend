package org.hifivee.minebackend.domain.project.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long project_id;

    @Column
    private String project_name;

    @Column
    private String userid;

    @Column
    private int headcount;

    @Column
    private String field;

    @Column
    private String habitat;

    /*public Project(String project_name, String user_id, int headcount, String field, String habitat) {
        this.project_name = project_name;
        this.user_id = user_id;
        this.headcount = headcount;
        this.field = field;
        this.habitat = habitat;
    }*/

    @Builder
    public Project(String project_name, String userid, int headcount, String field, String habitat){
        this.project_name = project_name;
        this.userid = userid;
        this.headcount = headcount;
        this.field = field;
        this.habitat = habitat;
    }

    public void update(String project_name, int headcount, String field, String habitat){
        this.project_name = project_name;
        this.headcount = headcount;
        this.field = field;
        this.habitat = habitat;
    }
}
