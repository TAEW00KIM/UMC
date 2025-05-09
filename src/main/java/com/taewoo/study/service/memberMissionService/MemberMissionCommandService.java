package com.taewoo.study.service.memberMissionService;

import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;

public interface MemberMissionCommandService {
    MissionChallengeResponseDTO.ChallengeResultDTO challenge(Long storeId, MissionChallengeRequestDTO.ChallengeDTO request);
}
