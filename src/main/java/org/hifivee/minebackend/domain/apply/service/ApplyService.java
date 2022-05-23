package org.hifivee.minebackend.domain.apply.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.domain.apply.dto.*;
import org.hifivee.minebackend.domain.apply.repository.Apply;
import org.hifivee.minebackend.domain.apply.repository.ApplyRepository;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.hifivee.minebackend.domain.recruit.repository.RecruitRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ApplyService {
    private final AccountRepository accountRepository;
    private final RecruitRepository recruitRepository;
    private final ApplyRepository applyRepository;

    @Transactional
    public void createApply(ApplyCreateRequestDto requestDto){
        Account userId = accountRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("지원하는 유저 정보가 없습니다"));
        Recruit recruitId = recruitRepository.findById(requestDto.getRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("해당 구인글이 존재하지 않습니다."));

        applyRepository.save(requestDto.toApply(userId, recruitId));
    }

    @Transactional
    public List<Apply> fetchApply(ApplyFetchRequestDto requestDto) {
        Apply apply = applyRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당하는 지원서가 없습니다."));
        List<Apply> applies = new ArrayList<>();
        applies.add(apply);

        return applies;
    }

    @Transactional
    public List<Apply> fetchMyApply(ApplyFetchRequestDto requestDto) {
        Account userId = accountRepository.findById(requestDto.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("해당 유저 정보가 없습니다"));

        List<Apply> applies = applyRepository.findByAccount(userId);

        if(applies.isEmpty()){
            return null;
        }
        else{
            return applies;
        }
    }

    @Transactional
    public List<Apply> fetchRecuritApply(ApplyFetchRequestDto requestDto) {
        Recruit recruitId = recruitRepository.findById(requestDto.getRecruitId())
                .orElseThrow(() -> new IllegalArgumentException("해당 구인글이 없습니다"));

        List<Apply> applies = applyRepository.findByRecruit(recruitId);

        if(applies.isEmpty()){
            return null;
        }
        else{
            return applies;
        }
    }

    @Transactional
    public void deleteApply(ApplyDeleteRequestDto requestDto) {
        Apply apply = applyRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 지원서가 없습니다"));

        applyRepository.delete(apply);
    }

    @Transactional
    public void updateApply(ApplyUpdateRequestDto requestDto) {
        Apply apply = applyRepository.findById(requestDto.getId())
                .orElseThrow(() -> new IllegalArgumentException("해당 지원서가 없습니다"));

        apply.update(requestDto.getApplyMessage());
    }
}
