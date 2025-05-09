package com.taewoo.study.converter;

import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;

public class MemberMissionConverter {
    public static MissionChallengeResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MissionChallengeResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .build();
    }
}
