package com.taewoo.study.web.controller;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.converter.MemberMissionConverter;
import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.service.memberMissionService.MemberMissionCommandService;
import com.taewoo.study.service.memberMissionService.MemberMissionQueryService;
import com.taewoo.study.validation.annotation.ValidPage;
import com.taewoo.study.web.dto.memberMissionDto.MemberMissionResponseDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeResponseDTO;
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
@RequiredArgsConstructor
@RequestMapping("/member-mission")
@Tag(name = "회원 미션 관리 API")
public class MemberMissionRestController {
    private final MemberMissionCommandService memberMissionCommandService;
    private final MemberMissionQueryService memberMissionQueryService;

    @Operation(summary = "미션 상태 변경", description = "도전 상태 변경")
    @PostMapping("/{storeId}/missions/challenge")
    public ApiResponse<MissionChallengeResponseDTO.ChallengeResultDTO> challengeMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionChallengeRequestDTO.ChallengeDTO request
            ) {
        return ApiResponse.onSuccess(memberMissionCommandService.challenge(storeId, request));
    }

    @GetMapping("/members/{memberId}/challenging")
    @Operation(summary = "내가 진행 중인 미션 목록 조회 API", description = "특정 회원이 '도전중' 상태인 미션 목록을 페이징하여 조회")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "MEMBER4001",
                    description = "사용자가 없습니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(
                    responseCode = "COMMON400",
                    description = "잘못된 요청입니다.",
                    content = @Content(schema = @Schema(implementation = ApiResponse.class)))
    })
    @Parameters({
            @Parameter(name = "memberId", description = "회원 ID(Path Variable)", required = true),
            @Parameter(name = "page", description = "페이지 번호(Query String)")
    })
    public ApiResponse<MemberMissionResponseDTO.MyChallengingMissionListDTO> getMyChallengingMissions(
            @PathVariable Long memberId,
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Integer pageZero = page - 1;
        Page<MemberMission> memberMissionPage = memberMissionQueryService.getMyChallengingMissions(memberId, pageZero);
        return ApiResponse.onSuccess(MemberMissionConverter.toMyChallengingMissionListDTO(memberMissionPage));
    }
}
