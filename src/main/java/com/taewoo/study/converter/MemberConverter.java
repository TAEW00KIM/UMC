package com.taewoo.study.converter;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.enums.Gender;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberConverter {
    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member) {
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDTO request) {
        Gender gender = null;

        switch (request.getGender()) {
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        Integer age = null;
        if (request.getBirthYear() != null) {
            Integer currentYear = LocalDate.now().getYear();
            age = currentYear - request.getBirthYear();
        }

        String phone = "010-0000-0000";

        return Member.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(gender)
                .age(age)
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())
                .phone(phone)
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
