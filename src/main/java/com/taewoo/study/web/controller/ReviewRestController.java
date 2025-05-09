package com.taewoo.study.web.controller;

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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
@Tag(name = "리뷰 API", description = "리뷰 작성 및 관리 API입니다.")
public class ReviewRestController {
    private final ReviewCommandService reviewCommandService;

    @PostMapping
    @Operation(summary = "리뷰 작성", description = "특정 가게에 리뷰를 작성합니다.")
    public ApiResponse<ReviewResponseDTO.CreateResultDTO> createReview(
            @RequestBody @Valid ReviewRequestDTO.CreateDTO request
            ) {
        Review review = reviewCommandService.createReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toCreateResultDTO(review));
    }
}
