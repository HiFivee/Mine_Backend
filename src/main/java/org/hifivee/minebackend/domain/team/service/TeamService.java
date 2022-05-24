package org.hifivee.minebackend.domain.team.service;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.repository.Account;
import org.hifivee.minebackend.domain.account.repository.AccountRepository;
import org.hifivee.minebackend.domain.recruit.dto.*;
import org.hifivee.minebackend.domain.recruit.repository.Recruit;
import org.hifivee.minebackend.domain.team.dto.*;
import org.hifivee.minebackend.domain.team.repository.Team;
import org.hifivee.minebackend.domain.team.repository.TeamRepository;
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
public class TeamService {
    private final TeamRepository teamRepository;
    private final AccountRepository accountRepository;

    @Transactional
    public void createTeam(TeamCreateRequestDto requestDto) {
        Account user = accountRepository.findById(requestDto.getUserId()).orElseThrow(() ->
                new IllegalArgumentException("유저 정보가 없습니다"));

        teamRepository.save(requestDto.toTeam(user));

    }

    @Transactional(readOnly = true)
    public TeamFetchResponseDto fetchTeam(TeamFetchRequestDto requestDto) {
        // 해당 id를 가진 팀 가져오기
        Long teamId = requestDto.getTeamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다. id: " + teamId));

        List<Team> teamList = new ArrayList<>();
        teamList.add(team);

        return new TeamFetchResponseDto(teamList);
    }

    //팀 전체 조회
    @Transactional(readOnly = true)
    public TeamFetchResponseDto fetchAllTeam(TeamFetchRequestDto requestDto) {

        Page<Team> teamPage;

        // 페이지 당 10개 가져오기
        int TEAM_PER_PAGE = 10;

        // 요청된 정보를 기반으로 Pageable 객체 생성
        int pageIndex = requestDto.getPageIndex() == null ? 0 : requestDto.getPageIndex();
        Pageable pageable = PageRequest.of(pageIndex, TEAM_PER_PAGE, Sort.Direction.DESC, "id");

        Long topId = requestDto.getTopId();
        if(topId == null) {
            teamPage = teamRepository.findAll(pageable);
        } else {
            teamPage = teamRepository.findAllByTopTeamId(topId, pageable);
        }

        return new TeamFetchResponseDto(teamPage.getContent(), teamPage.getPageable(), teamPage.isLast());
    }

    @Transactional
    public void updateTeam(TeamUpdateRequestDto requestDto) {
        Long teamId = requestDto.getTeamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다. id: " + teamId));

        // 구인글 업데이트 하기
       team.update(requestDto.getTeamName(), requestDto.getTeamNumber());

        teamRepository.save(team);
    }

    @Transactional
    public void deleteTeam(TeamDeleteRequestDto requestDto) {
        // 구인글 정보 가져오기
        Long teamId = requestDto.getTeamId();
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("해당 팀이 존재하지 않습니다. id: " + teamId));

        // 구인글 삭제하기
        teamRepository.delete(team);
    }
}
