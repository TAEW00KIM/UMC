package com.taewoo.study.converter;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.domain.Store;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import com.taewoo.study.web.dto.reviewDto.ReviewRequestDTO;
import com.taewoo.study.web.dto.reviewDto.ReviewResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class ReviewConverter {
    public static ReviewResponseDTO.CreateResultDTO toCreateResultDTO(Review review) {
        return ReviewResponseDTO.CreateResultDTO.builder()
                .reviewId(review.getId())
                .title(review.getTitle())
                .body(review.getBody())
                .score(review.getScore())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.CreateDTO request, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .title(request.getTitle())
                .body(request.getBody())
                .score(request.getScore())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return ReviewResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static ReviewResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<ReviewResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(ReviewConverter::reviewPreviewDTO).collect(Collectors.toList());

        return ReviewResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }

    public static MemberResponseDTO.MyReviewDTO toMyReviewDTO(Review review) {
        return MemberResponseDTO.MyReviewDTO.builder()
                .storeName(review.getStore().getName())
                .reviewId(review.getId())
                .score(review.getScore())
                .body(review.getBody())
                .createdAt(review.getCreatedAt() != null ? review.getCreatedAt().toLocalDate() : null)
                .build();
    }

    public static MemberResponseDTO.MyReviewListDTO toMyReviewListDTO(Page<Review> reviewPage) {
        List<MemberResponseDTO.MyReviewDTO> myReviewDTOList = reviewPage.getContent().stream()
                .map(ReviewConverter::toMyReviewDTO)
                .collect(Collectors.toList());

        return MemberResponseDTO.MyReviewListDTO.builder()
                .reviewList(myReviewDTOList)
                .listSize(myReviewDTOList.size())
                .totalPage(reviewPage.getTotalPages())
                .totalElements(reviewPage.getTotalElements())
                .isFirst(reviewPage.isFirst())
                .isLast(reviewPage.isLast())
                .build();
    }
}
