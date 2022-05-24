package org.hifivee.minebackend.domain.team.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.project.dto.ProjectCreateResponseDto;
import org.hifivee.minebackend.domain.recruit.dto.*;
import org.hifivee.minebackend.domain.team.dto.*;
import org.hifivee.minebackend.domain.team.service.TeamService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/team")
@RestController
public class TeamController {
    private final TeamService teamService;

    @PostMapping
    public ResponseEntity<TeamCreateResponseDto> createTeam(@RequestBody TeamCreateRequestDto requestDto){
        DtoMetaData dtoMetaData;
        try{
            teamService.createTeam(requestDto);
            dtoMetaData = new DtoMetaData("팀 생성 완료");
            return ResponseEntity.ok(new TeamCreateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new TeamCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<TeamFetchResponseDto> fetchTeam(@RequestBody TeamFetchRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            TeamFetchResponseDto responseDto;

            if(requestDto.getTeamId() != null) {
                responseDto = teamService.fetchTeam(requestDto);
            }
            else {
                responseDto = teamService.fetchAllTeam(requestDto);
            }

            dtoMetaData = new DtoMetaData("팀 정보 가져오기 성공");
            responseDto.setDtoMetaData(dtoMetaData);

            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TeamFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<TeamUpdateResponseDto> updateTeam(@RequestBody TeamUpdateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            teamService.updateTeam(requestDto);
            dtoMetaData = new DtoMetaData("팀 정보 업데이트 성공");
            return ResponseEntity.ok(new TeamUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TeamUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<TeamDeleteResponseDto> deleteRecruit(@RequestBody TeamDeleteRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            teamService.deleteTeam(requestDto);
            dtoMetaData = new DtoMetaData("구인 정보 삭제 성공");
            return ResponseEntity.ok(new TeamDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new TeamDeleteResponseDto(dtoMetaData));
        }
    }
}
