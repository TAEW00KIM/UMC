package com.taewoo.study.web.controller;

import com.taewoo.study.apiPayload.ApiResponse;
import com.taewoo.study.converter.MemberConverter;
import com.taewoo.study.converter.ReviewConverter;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.Review;
import com.taewoo.study.service.memberService.MemberCommandService;
import com.taewoo.study.service.memberService.MemberQueryService;
import com.taewoo.study.validation.annotation.ValidPage;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {
    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/join")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDTO request) {
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "내가 작성한 리뷰 목록 조회 API", description = "특정 회원이 작성한 리뷰들의 목록을 페이징하여 조회합니다. 페이지 번호는 1부터 시작합니다.")
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
            @Parameter(name = "memberId", description = "회원 ID (Path Variable)", required = true),
            @Parameter(name = "page", description = "페이지 번호 (Query String, 1부터 시작)", required = false)
    })
    public ApiResponse<MemberResponseDTO.MyReviewListDTO> getMyReviews(
            @PathVariable Long memberId,
            @ValidPage @RequestParam(name = "page", defaultValue = "1") Integer page
    ) {
        Integer pageZero = page - 1;
        Page<Review> reviewPage = memberQueryService.getMyReviewList(memberId, pageZero);
        return ApiResponse.onSuccess(ReviewConverter.toMyReviewListDTO(reviewPage));
    }

    @PostMapping("/login")
    @Operation(summary = "유저 로그인 API", description = "유저가 로그안하는 API입니다.")
    public ApiResponse<MemberResponseDTO.LoginResultDTO> login(@RequestBody @Valid MemberRequestDTO.LoginRequestDTO request) {
        return ApiResponse.onSuccess(memberCommandService.loginMember(request));
    }

    @GetMapping("/info")
    @Operation(summary = "유저 내 정보 조회 API - 인증 필요",
    description = "유저가 내 정보를 조회하는 API입니다.",
    security = { @SecurityRequirement(name = "JWT TOKEN")})
    public ApiResponse<MemberResponseDTO.MemberInfoDTO> getMyInfo(HttpServletRequest request) {
        return ApiResponse.onSuccess(memberQueryService.getMemberInfo(request));
    }
}
