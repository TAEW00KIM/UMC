package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionQueryService {
    List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit);
    List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit);
}
