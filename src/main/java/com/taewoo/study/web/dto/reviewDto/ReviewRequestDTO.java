package com.taewoo.study.web.dto.reviewDto;

import com.taewoo.study.validation.annotation.ExistStore;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ReviewRequestDTO {
    @Getter
    @NoArgsConstructor
    public static class CreateDTO {
        @ExistStore
        private Long storeId;

        @NotNull
        private Long memberId;

        @NotBlank
        private String title;

        @NotBlank
        private String body;

        @NotNull
        @Min(value = 0)
        @Max(value = 5)
        private Float score;
    }
}
