package com.taewoo.study.repository.memberRepository;

import com.taewoo.study.web.dto.MemberInfoDto;

public interface MemberRepositoryCustom {
    MemberInfoDto findMemberInfoById(Long memberId);
}
