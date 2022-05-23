package org.hifivee.minebackend.domain.project.dto;

import lombok.Data;

@Data
public class ProjectFetchRequestDto {
    /*
    fetch:
    private Long projectId;
     */

    /*
    myfetch:
    private Long userId;
     */

    /*
    allfetch:
    null;
     */

    /*
    filterfetch:
    private String projectName;
    private int headcount;
    private String field;
    private String habitat;
     */

    private Long projectId;
    private String projectName;
    private Long userId;
    private int headcount;
    private String field;
    private String habitat;
}
