package com.taewoo.study.validation.validator;

import com.taewoo.study.apiPayload.code.status.ErrorStatus;
import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.repository.memberMissionRepository.MemberMissionRepository;
import com.taewoo.study.validation.annotation.NotAlreadyChallenging;
import com.taewoo.study.web.dto.memberMissionDto.MissionChallengeRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotAlreadyChallengingValidator implements ConstraintValidator<NotAlreadyChallenging, MissionChallengeRequestDTO.ChallengeDTO> {
    private final MemberMissionRepository memberMissionRepository;

    @Override
    public void initialize(NotAlreadyChallenging constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MissionChallengeRequestDTO.ChallengeDTO request, ConstraintValidatorContext context) {
        if (request.getMemberId() == null || request.getMissionId() == null) return true;

        boolean alreadyChallenging = memberMissionRepository.existsByMemberIdAndMissionIdAndStatus(
                request.getMemberId(), request.getMissionId(), MissionStatus.CHALLENGING
        );

        if (alreadyChallenging) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(ErrorStatus.ALREADY_CHALLENGING.toString());
            return false;
        }

        return true;
    }
}
