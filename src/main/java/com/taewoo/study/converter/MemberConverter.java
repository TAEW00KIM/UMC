package com.taewoo.study.converter;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.enums.Gender;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;

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

        return Member.builder()
                .age(request.getAge())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .point(request.getPoint() == null ? 0 : request.getPoint())
                .name(request.getName())
                .phone(request.getPhone() == null ? "010-0000-0000" : request.getPhone())
                .memberPreferList(new ArrayList<>())
                .build();
    }
}
