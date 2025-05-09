package com.taewoo.study.service.reviewService;

import com.taewoo.study.domain.Review;
import com.taewoo.study.web.dto.reviewDto.ReviewRequestDTO;
import jakarta.validation.Valid;

public interface ReviewCommandService {
    Review createReview(@Valid ReviewRequestDTO.CreateDTO request);
}
