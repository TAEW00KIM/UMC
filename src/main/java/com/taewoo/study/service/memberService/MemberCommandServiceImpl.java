package com.taewoo.study.service.memberService;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.apiPayload.exception.handler.FoodCateoryHandler;
import com.taewoo.study.converter.MemberConverter;
import com.taewoo.study.converter.MemberPreferConverter;
import com.taewoo.study.domain.FoodCategory;
import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.mapping.MemberPrefer;
import com.taewoo.study.repository.foodCategoryRepository.FoodCategoryRepository;
import com.taewoo.study.repository.memberRepository.MemberRepository;
import com.taewoo.study.web.dto.memberDto.MemberRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService {
    private final MemberRepository memberRepository;
    private final FoodCategoryRepository foodCategoryRepository;

    @Override
    public Member joinMember(MemberRequestDTO.JoinDTO request) {
        Member newMember = MemberConverter.toMember(request);
        List<FoodCategory> foodCategoryList = request.getPreferCategory().stream()
                .map(category -> {
                    return foodCategoryRepository.findById(category).orElseThrow(() ->
                            new FoodCateoryHandler(ErrorStatus.FOOD_CATEGORY_NOT_FOUND));
                }).collect(Collectors.toList());
        List<MemberPrefer> memberPreferList = MemberPreferConverter.toMemberPreferList(foodCategoryList);

        memberPreferList.forEach(memberPrefer -> {memberPrefer.setMember(newMember);});

        return memberRepository.save(newMember);
    }
}
