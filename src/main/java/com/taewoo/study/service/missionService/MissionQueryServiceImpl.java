package com.taewoo.study.service.missionService;

import com.taewoo.study.domain.Mission;
import com.taewoo.study.repository.mission.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryServiceImpl implements MissionQueryService {
    private final MissionRepository missionRepository;

    @Override
    public List<Mission> findAvailableMissions(String regionName, Long memberId, Long cursorId, int limit) {
        return missionRepository.findAvailableMissions(regionName, memberId, cursorId, limit);
    }
}
