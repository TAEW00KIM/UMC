package com.taewoo.study.web.controller;

import com.taewoo.study.converter.ReviewConverter;
import com.taewoo.study.domain.Review;
import com.taewoo.study.service.storeService.StoreQueryService;
import com.taewoo.study.validation.annotation.ExistStore;
import com.taewoo.study.web.dto.reviewDto.ReviewResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
@Tag(name = "가게 API", description = "가게 등록/조회 등과 관련된 API입니다.")
public class StoreRestController {
    private final StoreCommandService storeCommandService;
    private final StoreQueryService storeQueryService;

    @PostMapping
    @Operation(summary = "가게 생성", description = "특정 지역에 가게를 새로 추가합니다.")
    public ApiResponse<StoreResponseDTO.CreateResultDTO> createStore(
            @RequestBody @Valid StoreRequestDTO.CreateDTO request) {
        Store store = storeCommandService.createStore(request);
        return ApiResponse.onSuccess(StoreConverter.toCreateResultDTO(store));

    }

    @GetMapping("/{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가게의 리뷰 목록을 조회하는 API이며 페이징을 포함합니다. query String으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",
                    description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH003",
                    description = "access 토큰이 필요합니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH004",
                    description = "access 토큰 만료",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "AUTH006",
                    description = "access 토큰에 이상 발생",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 ID, path variable")
    })
    public ApiResponse<ReviewResponseDTO.ReviewPreviewListDTO> getReviewList(
            @ExistStore @PathVariable(name = "storeId") Long storeId,
            @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList = storeQueryService.getReviewList(storeId, page);
        return ApiResponse.onSuccess(ReviewConverter.reviewPreviewListDTO(reviewList));
    }
}
