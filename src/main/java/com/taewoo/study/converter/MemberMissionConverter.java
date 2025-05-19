package com.taewoo.study.converter;

import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.web.dto.memberMissionDto.MemberMissionResponseDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMissionConverter {
    public static MissionChallengeResponseDTO.ChallengeResultDTO toChallengeResultDTO(MemberMission memberMission) {
        return MissionChallengeResponseDTO.ChallengeResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .memberId(memberMission.getMember().getId())
                .missionId(memberMission.getMission().getId())
                .status(memberMission.getStatus().name())
                .build();
    }

    public static MemberMissionResponseDTO.MyChallengingMissionDTO toMyChallengingMissionDTO(MemberMission memberMission) {
        return MemberMissionResponseDTO.MyChallengingMissionDTO.builder()
                .memberMissionId(memberMission.getId())
                .missionId(memberMission.getMission().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionSpec(memberMission.getMission().getMissionSpec())
                .reward(memberMission.getMission().getReward())
                .deadline(memberMission.getMission().getDeadline())
                .build();
    }

    public static MemberMissionResponseDTO.MyChallengingMissionListDTO toMyChallengingMissionListDTO(Page<MemberMission> memberMissionPage) {
        List<MemberMissionResponseDTO.MyChallengingMissionDTO> dtoList = memberMissionPage.getContent().stream()
                .map(MemberMissionConverter::toMyChallengingMissionDTO)
                .collect(Collectors.toList());

        return MemberMissionResponseDTO.MyChallengingMissionListDTO.builder()
                .missionList(dtoList)
                .listSize(dtoList.size())
                .totalPage(memberMissionPage.getTotalPages())
                .totalElements(memberMissionPage.getTotalElements())
                .isFirst(memberMissionPage.isFirst())
                .isLast(memberMissionPage.isLast())
                .build();
    }
}
