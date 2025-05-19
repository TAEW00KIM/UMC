package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;

public interface MemberMissionCommandService {
    MissionChallengeResponseDTO.ChallengeResultDTO challenge(Long storeId, MissionChallengeRequestDTO.ChallengeDTO request);
    MemberMission completeChallengingMission(Long memberId, Long memberMissionId);
}
