package com.taewoo.study.repository.missionRepository;

import com.taewoo.study.domain.Mission;

import java.util.List;

public interface MissionRepositoryCustom {
    List<Mission> findAvailableMissions(String regionName, Long memberId, Long cursorId, int limit);
}
