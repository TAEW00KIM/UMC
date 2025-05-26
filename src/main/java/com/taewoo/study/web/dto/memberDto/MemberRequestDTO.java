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
        Integer age;

        @NotNull
        Integer birthYear;

        @NotNull
        Integer birthMonth;

        @NotNull
        Integer birthDay;

        Integer point;

        String phone;

        @Size(min = 5, max = 12)
        String address;

        @Size(min = 5, max = 12)
        String specAddress;

        @ExistCategories
        List<Long> preferCategory;

        @NotNull
        Role role;
    }

    @Getter
    @Setter
    public static class LoginRequestDTO {
        @NotBlank(message = "이메일은 필수입니다.")
        @Email(message = "올바른 이메일 형식이어야 합니다.")
        private String email;

        @NotBlank(message = "패드워드는 필수입니다.")
        private String password;
    }
}
