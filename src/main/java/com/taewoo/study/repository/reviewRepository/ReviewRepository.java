package com.taewoo.study.repository.reviewRepository;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.domain.Store;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, Pageable pageable);
}
