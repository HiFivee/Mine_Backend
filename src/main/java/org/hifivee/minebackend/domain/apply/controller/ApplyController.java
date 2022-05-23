package org.hifivee.minebackend.domain.apply.controller;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.apply.dto.*;
import org.hifivee.minebackend.domain.apply.repository.Apply;
import org.hifivee.minebackend.domain.apply.service.ApplyService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/apply")
@RequiredArgsConstructor
@RestController
public class ApplyController {
    private final ApplyService applyService;

    @PostMapping
    public ResponseEntity<ApplyCreateResponseDto> createApply(@RequestBody ApplyCreateRequestDto requestDto) {
        DtoMetaData dtoMetaData;
        try {
            applyService.createApply(requestDto);
            dtoMetaData = new DtoMetaData("지원서 작성 성공");
            return ResponseEntity.ok(new ApplyCreateResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApplyCreateResponseDto(dtoMetaData));
        }
    }

    @GetMapping
    public ResponseEntity<ApplyFetchResponseDto> fetchApply(@RequestBody ApplyFetchRequestDto requestDto){
        DtoMetaData dtoMetaData;
        List<Apply> applies;
        try{
            if(requestDto.getRecruitId() != null){
                applies = applyService.fetchRecuritApply(requestDto);
                dtoMetaData = new DtoMetaData("해당 구인글에 대한 지원서 조회 성공");
            }
            else if(requestDto.getUserId() != null){
                applies = applyService.fetchMyApply(requestDto);
                dtoMetaData = new DtoMetaData("나의 지원서 조회 성공");
            }
            else {
                applies = applyService.fetchApply(requestDto);
                dtoMetaData = new DtoMetaData("지원서 조회 성공");
            }
            return ResponseEntity.ok(new ApplyFetchResponseDto(dtoMetaData, applies));
        } catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApplyFetchResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<ApplyDeleteResponseDto> deleteApply(@RequestBody ApplyDeleteRequestDto requestDto){
        DtoMetaData dtoMetaData;
        try{
            applyService.deleteApply(requestDto);
            dtoMetaData = new DtoMetaData("지원서 삭제 성공");
            return ResponseEntity.ok(new ApplyDeleteResponseDto((dtoMetaData)));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApplyDeleteResponseDto(dtoMetaData));
        }
    }

    @PutMapping
    public ResponseEntity<ApplyUpdateResponseDto> updateApply(@RequestBody ApplyUpdateRequestDto requestDto){
        DtoMetaData dtoMetaData;
        try{
            applyService.updateApply(requestDto);
            dtoMetaData = new DtoMetaData("지원서 수정 성공");
            return ResponseEntity.ok(new ApplyUpdateResponseDto(dtoMetaData));
        }
        catch (Exception e){
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ApplyUpdateResponseDto(dtoMetaData));
        }
    }
}
