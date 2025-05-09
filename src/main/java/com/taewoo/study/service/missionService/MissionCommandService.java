package com.taewoo.study.service.missionService;

import com.taewoo.study.domain.Mission;
import com.taewoo.study.web.dto.missionDto.MissionRequestDTO;
import jakarta.validation.Valid;

public interface MissionCommandService {
    Mission createMission(@Valid MissionRequestDTO.CreateDTO request);
}
