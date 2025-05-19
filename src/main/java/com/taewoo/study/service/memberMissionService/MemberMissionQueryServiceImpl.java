package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.MemberHandler;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.repository.memberMissionRepository.MemberMissionRepository;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {
    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    @Override
    public List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit) {
        return memberMissionRepository.findCompletedMissionByMember(memberId, cursorId, limit);
    }

    @Override
    public List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit) {
        return memberMissionRepository.findChallengingMissionByMember(memberId, cursorId, limit);
    }

    @Override
    public Page<MemberMission> getMyChallengingMissions(Long memberId, Integer page) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "createdAt"));
        return memberMissionRepository.findAllByMemberAndStatusWithDetails(member, MissionStatus.CHALLENGING, pageable);
    }
}
