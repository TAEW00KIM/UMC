package com.taewoo.study.service.reviewService;

import com.taewoo.study.domain.Review;

public interface ReviewCommandService {
    Review saveReview(Long memberId, Long storeId, String body, Float score);
}
