package com.taewoo.study.service.reviewService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.MemberHandler;
import com.taewoo.study.apiPayload.exception.handler.StoreHandler;
import com.taewoo.study.converter.ReviewConverter;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.domain.Store;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.repository.reviewRepository.ReviewRepository;
import com.taewoo.study.repository.storeRepository.StoreRepository;
import com.taewoo.study.web.dto.reviewDto.ReviewRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ReviewCommandServiceImpl implements ReviewCommandService {
    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    @Override
    public Review createReview(ReviewRequestDTO.CreateDTO request) {
        Store store = storeRepository.findById(request.getStoreId())
                .orElseThrow(() -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND));
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Review review = ReviewConverter.toReview(request, member, store);
        return reviewRepository.save(review);
    }
}
