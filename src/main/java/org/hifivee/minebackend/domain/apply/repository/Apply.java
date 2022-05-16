package org.hifivee.minebackend.domain.apply.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.implementation.bind.annotation.BindingPriority;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Apply {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long applyId;

    @ManyToOne(targetEntity = Account.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "userId", referencedColumnName = "id")
    Account account;

    @Column
    String applyMessage;

    @ManyToOne(targetEntity = Recruit.class, fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "recruitId", referencedColumnName = "id")
    Recruit recruit;

    @Builder
    public Apply(Long applyId, Account account, String applyMessage, Recruit recruit){
        this.applyId = applyId;
        this.account = account;
        this.applyMessage = applyMessage;
        this.recruit = recruit;
    }
}
