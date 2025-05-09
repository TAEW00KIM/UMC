package com.taewoo.study.service.missionService;

import com.taewoo.study.domain.Mission;

import java.util.List;

public interface MissionQueryService {
    List<Mission> findAvailableMissions(String regionName, Long memberId, Long cursorId, int limit);
}
