package org.hifivee.minebackend.domain.devArea.controller;

import lombok.RequiredArgsConstructor;
import org.hifivee.minebackend.domain.account.dto.*;
import org.hifivee.minebackend.domain.devArea.dto.*;
import org.hifivee.minebackend.domain.devArea.repository.DevArea;
import org.hifivee.minebackend.domain.devArea.service.DevAreaService;
import org.hifivee.minebackend.global.dto.DtoMetaData;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/devArea")
public class DevAreaController {
    private final DevAreaService devAreaService;

    @PostMapping
    public ResponseEntity<DevAreaCreateResponseDto> createDevArea(@RequestBody DevAreaCreateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            devAreaService.createDevArea(requestDto);
            dtoMetaData = new DtoMetaData("계정 생성 성공");
            return ResponseEntity.ok(new DevAreaCreateResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DevAreaCreateResponseDto(dtoMetaData));
        }
    }
    //개발스택 조회
    @GetMapping
    public ResponseEntity<DevAreaFetchResponseDto> fetchDevArea(@RequestBody DevAreaFetchRequestDto requestDto) {
        DtoMetaData dtoMetaData;
        try{
            DevArea devArea = devAreaService.fetchDevArea(requestDto);
            dtoMetaData = new DtoMetaData("계정 정보 가져오기 성공");
            return ResponseEntity.ok(new DevAreaFetchResponseDto(dtoMetaData, devArea));
        }catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DevAreaFetchResponseDto(dtoMetaData));
        }

    }

    //개발스택 업데이트
    @PutMapping
    public ResponseEntity<DevAreaUpdateResponseDto> updateDevArea(@RequestBody DevAreaUpdateRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            devAreaService.updateDevArea(requestDto);
            dtoMetaData = new DtoMetaData("개발분야 업데이트 성공");
            return ResponseEntity.ok(new DevAreaUpdateResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DevAreaUpdateResponseDto(dtoMetaData));
        }
    }

    @DeleteMapping
    public ResponseEntity<DevAreaDeleteResponseDto> deleteDevArea(@RequestBody DevAreaDeleteRequestDto requestDto) {
        DtoMetaData dtoMetaData;

        try {
            devAreaService.deleteDevArea(requestDto);
            dtoMetaData = new DtoMetaData("개발분야 삭제 성공");
            return ResponseEntity.ok(new DevAreaDeleteResponseDto(dtoMetaData));
        } catch (Exception e) {
            dtoMetaData = new DtoMetaData(e.getMessage(), e.getClass().getName());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new DevAreaDeleteResponseDto(dtoMetaData));
        }
    }




}
