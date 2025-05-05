package com.taewoo.study.service.memberService;

import com.taewoo.study.web.dto.MemberInfoDto;

public interface MemberQueryService {
    MemberInfoDto findMemberInfoById(Long memberId);
}
