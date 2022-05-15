package org.hifivee.minebackend.domain.recruit.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.recruit.dto.*;
import org.hifivee.minebackend.domain.recruit.service.RecruitService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api/recruit")
@RestController
public class RecruitController {

    private final RecruitService recruitService;

    @PostMapping
    public ResponseEntity<RecruitCreateResponseDto> createRecruit(@RequestBody RecruitCreateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            recruitService.createRecruit(requestDto);
            dtoMetaData = new DtoMetaData("구인 정보 생성 성공");
            return ResponseEntity.ok(new RecruitCreateResponseDto(dtoMetaData));
        }
       catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RecruitCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<RecruitFetchResponseDto> fetchRecruit(@RequestBody RecruitFetchRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            RecruitFetchResponseDto responseDto;

            if(requestDto.getId() != null) {
                responseDto = recruitService.fetchRecruit(requestDto);
            }
            else {
                responseDto = recruitService.fetchAllRecruit(requestDto);
            }

            dtoMetaData = new DtoMetaData("구인 정보 가져오기 성공");
            responseDto.setDtoMetaData(dtoMetaData);

            return ResponseEntity.ok(responseDto);
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RecruitFetchResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<RecruitUpdateResponseDto> updateRecruit(@RequestBody RecruitUpdateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            recruitService.updateRecruit(requestDto);
            dtoMetaData = new DtoMetaData("구인 정보 업데이트 성공");
            return ResponseEntity.ok(new RecruitUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RecruitUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<RecruitDeleteResponseDto> deleteRecruit(@RequestBody RecruitDeleteRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            recruitService.deleteRecruit(requestDto);
            dtoMetaData = new DtoMetaData("구인 정보 삭제 성공");
            return ResponseEntity.ok(new RecruitDeleteResponseDto(dtoMetaData));
        }
        catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new RecruitDeleteResponseDto(dtoMetaData));
        }
    }
}
