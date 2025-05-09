package com.taewoo.study.web.dto.memberMissionDto;

import com.taewoo.study.validation.annotation.NotAlreadyChallenging;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionChallengeRequestDTO {
    @Getter
    @NoArgsConstructor
    @NotAlreadyChallenging
    public static class ChallengeDTO {
        @NotNull
        private Long memberId;

        @NotNull
        private Long missionId;
    }
}
