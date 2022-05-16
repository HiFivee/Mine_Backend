package org.hifivee.minebackend.domain.apply.repository;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.account.repository.Account;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Apply {
    @Id
    Long applyId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "applyId", referencedColumnName = "id")
    Account account;

    @Column
    String applyMessage;

    @ManyToOne(targetEntity = Recruit.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recruitId", referencedColumnName = "id")
    Recruit recruit;

}
