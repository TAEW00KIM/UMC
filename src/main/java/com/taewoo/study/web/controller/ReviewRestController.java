package com.taewoo.study.web.controller;

import com.taewoo.study.service.memberService.MemberQueryService;
import com.taewoo.study.service.storeService.StoreQueryService;
import com.taewoo.study.validation.annotation.ExistStore;
import com.taewoo.study.validation.annotation.ValidPage;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.converter.ReviewConverter;
import com.taewoo.study.domain.Review;
import com.taewoo.study.service.reviewService.ReviewCommandService;
import com.taewoo.study.web.dto.reviewDto.ReviewRequestDTO;
import com.taewoo.study.web.dto.reviewDto.ReviewResponseDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 작성 및 관리 API입니다.")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;
    private final StoreQueryService storeQueryService;
    private final MemberQueryService memberQueryService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다.")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateDTO request
            ) {
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDTO(review));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰들의 목록을 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "MEMBER4001",
                    description = "사용자가 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID (Path Variable)", required = true),
            @Parameter(name = "page", description = "페이지 번호 (Query String, 1부터 시작)", required = false)
    })
    public ApiResponse<MemberResponseDTO.MyReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Integer pageZero = page - 1;
        Page<Review> reviewPage = memberQueryService.getMyReviewList(memberId, pageZero);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewPage));
    }
}
