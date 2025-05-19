package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MemberMissionQueryService {
    List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit);
    List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit);

    Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page);
}
