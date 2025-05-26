package com.taewoo.study.service.memberService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.FoodCateoryHandler;
import com.taewoo.study.apiPayload.exception.handler.MemberHandler;
import com.taewoo.study.config.security.jwt.JwtTokenProvider;
import com.taewoo.study.converter.MemberConverter;
import com.taewoo.study.converter.MemberPreferConverter;
import com.taewoo.study.domain.FoodCategory;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.mapping.MemberPrefer;
import com.taewoo.study.repository.foodCategoryRepository.FoodCategoryRepository;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import com.taewoo.study.web.dto.memberDto.MemberResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    @Transactional
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        newMember.encodePassword(passwordEncoder.encode(request.getPassword()));

        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() ->
                            new FoodCateoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }

    @Override
    public MemberResponseDTO.LoginResultDTO loginMember(MemberRequestDTO.LoginRequestDTO request) {
        Member member = memberRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND));

        if (!passwordEncoder.matches(request.getPassword(), member.getPassword())) {
            throw new MemberHandler(ErrorStatus.INVALID_PASSWORD);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                member.getEmail(), null,
                Collections.singleton(() -> member.getRole().name())
        );

        String accessToken = jwtTokenProvider.generateToken(authentication);

        return MemberConverter.toLoginResultDTO(
                member.getId(),
                accessToken
        );
    }
}
