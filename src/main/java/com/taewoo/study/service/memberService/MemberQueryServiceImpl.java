package com.taewoo.study.service.memberService;

import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.web.dto.memberDto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberQueryServiceImpl implements MemberQueryService {
    private final MemberRepository memberRepository;

    @Override
    public MemberInfoDto findMemberInfoById(Long memberId) {
        return null;
    }
}
