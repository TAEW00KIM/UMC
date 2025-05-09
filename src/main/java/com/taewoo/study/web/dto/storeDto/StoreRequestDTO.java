package com.taewoo.study.web.dto.storeDto;

import com.taewoo.study.validation.annotation.ExistRegion;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public class StoreRequestDTO {
    @Getter
    public static class CreateDTO {
        @ExistRegion
        private Long regionId;

        @NotBlank
        private String name;

        @NotBlank
        private String address;
    }
}
