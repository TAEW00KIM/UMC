package com.taewoo.study.web.controller;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.service.memberMissionService.MemberMissionCommandService;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/member-mission")
@Tag(name = "회원 미션 관리 API")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;

    @Operation(summary = "미션 상태 변경", description = "도전 상태 변경")
    @PostMapping("/{storeId}/missions/challenge")
    public ApiResponse<MissionChallengeResponseDTO.ChallengeResultDTO> challengeMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionChallengeRequestDTO.ChallengeDTO request
            ) {
        return ApiResponse.onSuccess(memberMissionCommandService.challenge(storeId, request));
    }
}
