package com.taewoo.study.repository.reviewRepository;

import com.taewoo.study.domain.*;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@RequiredArgsConstructor
public class ReviewRepositoryImpl implements ReviewRepositoryCustom {
    private final EntityManager entityManager;

    @Override
    @Transactional
    public Review saveReview(Review review) {
        entityManager.persist(review);
        return review;
    }
}
