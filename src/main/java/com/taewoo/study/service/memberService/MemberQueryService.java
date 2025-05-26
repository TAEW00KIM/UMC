package com.taewoo.study.service.memberService;

import com.taewoo.study.domain.Review;
import com.taewoo.study.web.dto.memberDto.MemberInfoDto;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Page;

public interface MemberQueryService {
    MemberInfoDto findMemberInfoById(Long memberId);
    Page<Review> getMyReviewList(Long memberId, Integer page);
    MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request);
}
