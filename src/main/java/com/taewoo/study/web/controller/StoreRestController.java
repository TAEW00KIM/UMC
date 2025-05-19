package com.taewoo.study.web.controller;

import com.taewoo.study.converter.MissionConverter;
import com.taewoo.study.converter.ReviewConverter;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Review;
import com.taewoo.study.service.missionService.MissionQueryService;
import com.taewoo.study.service.storeService.StoreQueryService;
import com.taewoo.study.validation.annotation.ExistStore;
import com.taewoo.study.validation.annotation.ValidPage;
import com.taewoo.study.web.dto.missionDto.MissionResponseDTO;
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
    private final MissionQueryService missionQueryService;

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

    @GetMapping("/{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가게에 등록된 미션 목록을 페이징하여 조회합니다. 페이지는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON200",
                    description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "STORE4001",
                    description = "해당 가게가 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게 ID (Path Variable)", required = true),
            @Parameter(name = "page", description = "페이지 번호 (Query String, 1부터 시작)")
    })
    public ApiResponse<MissionResponseDTO.StoreMissionListDTO> getStoreMissions(
            @ExistStore @PathVariable Long storeId,
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Integer pageZero = page - 1;
        Page<Mission> missionPage = missionQueryService.getStoreMissionList(storeId, pageZero);
        return ApiResponse.onSuccess(MissionConverter.toStoreMissionListDTO(missionPage));
    }
}
