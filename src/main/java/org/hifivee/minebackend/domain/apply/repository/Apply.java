package org.hifivee.minebackend.domain.apply.repository;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.hifivee.minebackend.global.entity.BaseEntity;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Apply extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applyId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    private Account account;

    @Column
    private String applyMessage;

    @ManyToOne(targetEntity = Recruit.class, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recruitId", referencedColumnName = "id")
    private Recruit recruit;

    @Builder
    public Apply(Long applyId, Account account, String applyMessage, Recruit recruit){
        this.applyId = applyId;
        this.account = account;
        this.applyMessage = applyMessage;
        this.recruit = recruit;
    }

    public void update(String applyMessage){
        this.applyMessage = applyMessage;
    }
}
