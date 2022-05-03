package org.hifivee.minebackend.domain.recruit.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.recruit.dto.*;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.hifivee.minebackend.domain.recruit.repository.RecruitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class RecruitService {

    private final RecruitRepository recruitRepository;

    @Transactional
    public void createRecruit(RecruitCreateRequestDto requestDto) {
        // 기존 프로젝트로 구인글이 생성되어 있는지 확인
        Long projectId = requestDto.getProjectId();
        if(recruitRepository.existsByProjectId(projectId)) {
            throw new IllegalArgumentException("이미 해당 프로젝트로 구인글이 생성되어 있습니다. projectId: " + projectId);
        }

        recruitRepository.save(requestDto.toEntity());
    }

    @Transactional(readOnly = true)
    public RecruitFetchResponseDto fetchRecruit(RecruitFetchRequestDto requestDto) {
        // 해당 id를 가진 구인글 가져오기
        Long id = requestDto.getId();
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 구인글이 존재하지 않습니다. id: " + id));

        // 구인글 리스트에 추가하기
        List<Recruit> recruitList = new ArrayList<>();
        recruitList.add(recruit);

        return new RecruitFetchResponseDto(recruitList);
    }

    @Transactional(readOnly = true)
    public RecruitFetchResponseDto fetchAllRecruit(RecruitFetchRequestDto requestDto) {
        Page<Recruit> recruitPage;

        // 페이지 당 10개 가져오기
        int RECRUIT_PER_PAGE = 10;

        // 요청된 정보를 기반으로 Pageable 객체 생성
        int pageIndex = requestDto.getPageIndex() == null ? 0 : requestDto.getPageIndex();
        Pageable pageable = PageRequest.of(pageIndex, RECRUIT_PER_PAGE, Sort.Direction.DESC, "id");

        Long topId = requestDto.getTopId();
        if(topId == null) {
            recruitPage = recruitRepository.findAll(pageable);
        } else {
            recruitPage = recruitRepository.findAllByTopRecruitId(topId, pageable);
        }

        return new RecruitFetchResponseDto(recruitPage.getContent(), recruitPage.getPageable(), recruitPage.isLast());
    }

    @Transactional
    public void updateRecruit(RecruitUpdateRequestDto requestDto) {
        // 구인글 정보 가져오기
        Long id = requestDto.getId();
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 구인글이 존재하지 않습니다. id: " + id));

        // 구인글 업데이트 하기
        recruit.update(requestDto.getProjectId(), requestDto.getDescription(),
                requestDto.getAttachments(), requestDto.getOpenings(), requestDto.getApplicants());

        recruitRepository.save(recruit);
    }

    @Transactional
    public void deleteRecruit(RecruitDeleteRequestDto requestDto) {
        // 구인글 정보 가져오기
        Long id = requestDto.getId();
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 구인글이 존재하지 않습니다. id: " + id));

        // 구인글 삭제하기
        recruitRepository.delete(recruit);
    }
}