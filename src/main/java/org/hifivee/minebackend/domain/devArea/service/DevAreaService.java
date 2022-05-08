package org.hifivee.minebackend.domain.devArea.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.devArea.dto.DevAreaCreateRequestDto;
import org.hifivee.minebackend.domain.devArea.dto.DevAreaDeleteRequestDto;
import org.hifivee.minebackend.domain.devArea.dto.DevAreaFetchRequestDto;
import org.hifivee.minebackend.domain.devArea.dto.DevAreaUpdateRequestDto;
import org.hifivee.minebackend.domain.devArea.repository.DevArea;
import org.hifivee.minebackend.domain.devArea.repository.DevAreaRepository;
import org.hifivee.minebackend.global.security.SecurityUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class DevAreaService {
    private final DevAreaRepository devAreaRepository;

    //회원에 대한 기술스택 생성
    @Transactional
    public void createDevArea(DevAreaCreateRequestDto requestDto){
        devAreaRepository.save(requestDto.toEntity());
    }

    //회원 개발스택 조회
    @Transactional
    public DevArea fetchDevArea(DevAreaFetchRequestDto requestDto) {
        Long id = requestDto.getId();
        DevArea techStack = devAreaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다."));
        return techStack;
    }

    //회원 개발스택 업데이트
    @Transactional
    public void updateDevArea(DevAreaUpdateRequestDto requestDto){
        Long id = requestDto.getId();
        DevArea devArea = devAreaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다."));
        devArea.DevAreaUpdate(devArea.getUserName(), devArea.getUserTechStack());

        devAreaRepository.save(devArea);
    }

    //회원 개발스택 정보 삭제(수정)
    @Transactional
    public void deleteDevArea(DevAreaDeleteRequestDto requestDto){
        Long id = requestDto.getId();
        DevArea devArea = devAreaRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 id를 가진 유저가 없습니다."));
        devAreaRepository.delete(devArea);
    }

}
