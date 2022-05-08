package org.hifivee.minebackend.domain.devArea.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DevArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

    @Column
    private String userName;

    @Column
    private String userTechStack;

    @Builder
    public DevArea(String userName,String userTechStack, Long userId){
        this.userName = userName;
        this.userTechStack = userTechStack;
        this.userId = userId;
    }

    public void DevAreaUpdate(String userName, String userDevArea){
        this.userName = userName;
        this.userTechStack = userDevArea;
    }
}
