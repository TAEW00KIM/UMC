package com.taewoo.study.web.dto.memberMissionDto;

import com.taewoo.study.validation.annotation.NotAlreadyChallenging;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

public class MissionChallengeResponseDTO {
    @Getter
    @Builder
    @NotAlreadyChallenging
    @AllArgsConstructor
    public static class ChallengeResultDTO {
        private Long memberMissionId;
        private Long missionId;
        private Long memberId;
        private String status;
    }
}
