package com.taewoo.study.web.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.converter.StoreConverter;
import com.taewoo.study.domain.Store;
import com.taewoo.study.service.storeService.StoreCommandService;
import com.taewoo.study.web.dto.storeDto.StoreRequestDTO;
import com.taewoo.study.web.dto.storeDto.StoreResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "가게 API", description = "가게 등록/조회 등과 관련된 API입니다.")
public class StoreRestController {
    private final StoreCommandService storeCommandService;

    @PostMapping
    @Operation(summary = "가게 생성", description = "특정 지역에 가게를 새로 추가합니다.")
    public ApiResponse<StoreResponseDTO.CreateResultDTO> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(store));

    }
}
