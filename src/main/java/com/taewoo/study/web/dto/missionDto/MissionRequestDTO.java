package com.taewoo.study.web.dto.missionDto;

import com.taewoo.study.validation.annotation.ExistStore;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

public class MissionRequestDTO {
    @Getter
    @AllArgsConstructor
    public static class CreateDTO {
        @ExistStore
        @NotNull
        private Long storeId;

        @NotBlank
        private String missionSpec;

        @NotNull
        private Integer reward;

        @Future
        @NotNull
        private LocalDate deadline;
    }
}
