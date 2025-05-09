package com.taewoo.study.service.memberService;

import com.taewoo.study.web.dto.memberDto.MemberInfoDto;

public interface MemberQueryService {
    MemberInfoDto findMemberInfoById(Long memberId);
}
