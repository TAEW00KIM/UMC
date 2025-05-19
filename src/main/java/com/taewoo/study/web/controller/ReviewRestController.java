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
}
