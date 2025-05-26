package com.taewoo.study.web.dto.memberDto;

import com.taewoo.study.domain.enums.Role;
import com.taewoo.study.validation.annotation.ExistCategories;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class MemberRequestDTO {
    @Getter
    @Setter
    public static class JoinDTO {
        @NotBlank
        String name;

        @NotBlank
        @Email
        String email;

        @NotBlank
        String password;

        @NotNull
        Integer gender;

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;

        @NotNull
        Role role;
    }
}
