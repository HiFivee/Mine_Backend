package org.hifivee.minebackend.domain.devArea.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hifivee.minebackend.domain.devArea.repository.DevArea;

@Data
@NoArgsConstructor
public class DevAreaCreateRequestDto {
    private Long userId;
    private String userName;
    private String userTechStack;


    //dto -> entity
    public DevArea toEntity(){
        return DevArea.builder()
                .userId(userId)
                .userName(userName)
                .userTechStack(userTechStack)
                .build();
    }
}
