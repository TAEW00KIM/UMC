package com.taewoo.study.service.reviewService;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.domain.Store;
import com.taewoo.study.repository.reviewRepository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;

    @Override
    public Review saveReview(Long memberId, Long storeId, String body, Float score) {
        Review review = Review.builder()
                .member(Member.builder().id(memberId).build())
                .store(Store.builder().id(storeId).build())
                .body(body)
                .score(score)
                .createdAt(LocalDateTime.now())
                .build();

        return reviewRepository.save(review);
    }
}
