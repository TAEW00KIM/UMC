package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.repository.memberMissionRepository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit) {
        return memberMissionRepository.findCompletedMissionByMember(memberId, cursorId, limit);
    }

    @Override
    public List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit) {
        return memberMissionRepository.findChallengingMissionByMember(memberId, cursorId, limit);
    }
}
