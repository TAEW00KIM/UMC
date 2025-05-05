package com.taewoo.study.repository.memberMissionRepository;

import com.taewoo.study.domain.mapping.MemberMission;

import java.util.List;

public interface MemberMissionRepositoryCustom {
    List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit);
    List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit);
}
