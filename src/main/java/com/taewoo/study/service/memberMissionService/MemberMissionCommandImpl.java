package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.MemberHandler;
import com.taewoo.study.apiPayload.exception.handler.MemberMissionHandler;
import com.taewoo.study.apiPayload.exception.handler.MissionHandler;
import com.taewoo.study.converter.MemberMissionConverter;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.repository.memberMissionRepository.MemberMissionRepository;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.repository.missionRepository.MissionRepository;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandImpl implements MemberMissionCommandService {
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final MemberRepository memberRepository;

    @Override
    @Transactional
    public MissionChallengeResponseDTO.ChallengeResultDTO challenge(Long storeId, MissionChallengeRequestDTO.ChallengeDTO request) {
        Long missionId = request.getMissionId();
        Long memberId = request.getMemberId();

        boolean isAlreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                memberId, missionId, MissionStatus.CHALLENGING
        );

        if (isAlreadyChallenging) {
            throw new MemberMissionHandler(ErrorStatus.ALREADY_CHALLENGING);
        }

        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND));
        if (!mission.getStore().getId().equals(storeId)) {
            throw new MissionHandler(ErrorStatus.MISSON_NOT_IN_STORE);
        }

        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        MemberMission newChallenge = MemberMission.builder()
                .member(member)
                .mission(mission)
                .status(MissionStatus.CHALLENGING)
                .build();

        memberMissionRepository.save(newChallenge);

        return MemberMissionConverter.toChallengeResultDTO(newChallenge);
    }
}
