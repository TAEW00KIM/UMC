package com.taewoo.study.service.memberService;

import com.taewoo.study.domain.Member;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import jakarta.validation.Valid;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.@Valid JoinDTO request);
    MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request);
}
