package com.taewoo.study.converter;

import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Store;
import com.taewoo.study.web.dto.missionDto.MissionRequestDTO;
import com.taewoo.study.web.dto.missionDto.MissionResponseDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {
    public static MissionResponseDTO.CreateResultDTO toCreateResultDTO(Mission mission) {
        return MissionResponseDTO.CreateResultDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline().toString())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.CreateDTO request, Store store) {
        return Mission.builder()
                .store(store)
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .build();
    }

    public static MissionResponseDTO.StoreMissionDTO toStoreMissionDTO(Mission mission) {
        return MissionResponseDTO.StoreMissionDTO.builder()
                .missionId(mission.getId())
                .missionSpec(mission.getMissionSpec())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }

    public static MissionResponseDTO.StoreMissionListDTO toStoreMissionListDTO(Page<Mission> missionPage) {
        List<MissionResponseDTO.StoreMissionDTO> missionDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toStoreMissionDTO)
                .collect(Collectors.toList());

        return MissionResponseDTO.StoreMissionListDTO.builder()
                .missionList(missionDTOList)
                .listSize(missionDTOList.size())
                .totalPage(missionPage.getTotalPages())
                .totalElements(missionPage.getTotalElements())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .build();
    }
}
