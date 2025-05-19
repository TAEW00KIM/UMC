package com.taewoo.study.service.memberService;

import com.taewoo.study.domain.Review;
import com.taewoo.study.web.dto.memberDto.MemberInfoDto;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    MemberInfoDto findMemberInfoById(Long memberId);
    Page<Review> getMyReviewList(Long memberId, Integer page);
}
