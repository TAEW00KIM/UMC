package com.taewoo.study.converter;

import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.Store;
import com.taewoo.study.web.dto.missionDto.MissionRequestDTO;
import com.taewoo.study.web.dto.missionDto.MissionResponseDTO;

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
}
