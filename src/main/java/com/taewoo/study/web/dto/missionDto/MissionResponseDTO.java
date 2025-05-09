package com.taewoo.study.web.dto.missionDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MissionResponseDTO {
    @Getter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateResultDTO {
        private Long missionId;
        private String missionSpec;
        private Integer reward;
        private String deadline;
    }
}
