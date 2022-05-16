package org.hifivee.minebackend.domain.apply.dto;

import lombok.Data;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.apply.repository.Apply;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;

@Data
public class ApplyCreateRequestDto {
    private Long userId;
    private String applyMessage;
    private Long recruitId;

    public Apply toApply(Account userId, Recruit recruitId){
        return Apply.builder()
                .account(userId)
                .applyMessage(applyMessage)
                .recruit(recruitId)
                .build();
    }
}
