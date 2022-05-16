package org.hifivee.minebackend.domain.devArea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.devArea.repository.DevArea;

@Data
@NoArgsConstructor
public class DevAreaCreateRequestDto {
    private String techStack;
    private Long projectId;
    private Long userId;


    //dto -> entity
    public DevArea toEntity(Account account){
        return DevArea.builder()
                .techStack(techStack)
                .projectId(projectId)
                .account(account)
                .build();
    }
}
