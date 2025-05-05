package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.repository.memberMissionRepository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandImpl implements MemberMissionCommandService {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public MemberMission saveMemberMission(MemberMission memberMission) {
        return memberMissionRepository.save(memberMission);
    }
}
