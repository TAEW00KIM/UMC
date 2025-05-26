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
                .name(request.getName())
                .email(request.getEmail())
                .password(request.getPassword())
                .gender(gender)
                .age(request.getAge())
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .role(request.getRole())
                .point(request.getPoint() != null ? request.getPoint() : 0)
                .phone(request.getPhone())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberResponseDTO.LoginResultDTO toLoginResultDTO(Long memberId, String accessToken) {
        return MemberResponseDTO.LoginResultDTO.builder()
                .memberId(memberId)
                .accessToken(accessToken)
                .build();
    }

    public static MemberResponseDTO.MemberInfoDTO toMemberInfoDTO(Member member) {
        String genderString = null;
        if (member.getGender() != null) {
            genderString = member.getGender().name();
        }

        return MemberResponseDTO.MemberInfoDTO.builder()
                .name(member.getName())
                .email(member.getEmail())
                .gender(genderString)
                .build();
    }
}
