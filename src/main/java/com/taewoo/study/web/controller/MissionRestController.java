package com.taewoo.study.web.controller;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.converter.MissionConverter;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.service.missionService.MissionCommandService;
import com.taewoo.study.service.missionService.MissionQueryService;
import com.taewoo.study.validation.annotation.ExistStore;
import com.taewoo.study.validation.annotation.ValidPage;
import com.taewoo.study.web.dto.missionDto.MissionRequestDTO;
import com.taewoo.study.web.dto.missionDto.MissionResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/missions")
@RequiredArgsConstructor
@Tag(name = "미션 API", description = "미션 관련 API")
public class MissionRestController {
    private final MissionCommandService missionCommandService;
    private final MissionQueryService missionQueryService;

    @Operation(summary = "미션 생성", description = "가게에 미션을 추가합니다.")
    @PostMapping
    public ApiResponse<MissionResponseDTO.CreateResultDTO> createMission(
            @RequestBody @Valid MissionRequestDTO.CreateDTO request
            ) {
        Mission mission = missionCommandService.createMission(request);
        return ApiResponse.onSuccess(MissionConverter.toCreateResultDTO(mission));
    }
}
