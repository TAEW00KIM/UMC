package com.taewoo.study.service.memberService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.MemberHandler;
import com.taewoo.study.config.security.jwt.JwtTokenProvider;
import com.taewoo.study.converter.MemberConverter;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.repository.reviewRepository.ReviewRepository;
import com.taewoo.study.web.dto.memberDto.MemberInfoDto;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;
    private final ReviewRepository reviewRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public MemberInfoDto findMemberInfoById(Long memberId) {
        return null;
    }

    @Override
    public Page<Review> getMyReviewList(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));

        return reviewRepository.findAllByMember(member, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public MemberResponseDTO.MemberInfoDTO getMemberInfo(HttpServletRequest request) {
        Authentication authentication = jwtTokenProvider.extractAuthentication(request);
        String email = authentication.getName();

        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));
        return MemberConverter.toMemberInfoDTO(member);
    }
}
