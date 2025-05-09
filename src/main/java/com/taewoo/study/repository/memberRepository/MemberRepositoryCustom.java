package com.taewoo.study.repository.memberRepository;

import com.taewoo.study.web.dto.memberDto.MemberInfoDto;

public interface MemberRepositoryCustom {
    MemberInfoDto findMemberInfoById(Long memberId);
}
