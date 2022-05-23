package org.hifivee.minebackend.domain.devArea.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.account.repository.Account;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class DevArea {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @OneToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "accountId", referencedColumnName = "Id")
    private Account account;

    @Column
    private Long projectId;

    @Column
    private String techStack;

    @Builder
    public DevArea(Account account, String techStack, Long projectId){
        this.account = account;
        this.techStack = techStack;
        this.projectId = projectId;
    }

    public void DevAreaUpdate(String userDevArea){
        this.techStack = userDevArea;
    }
}
