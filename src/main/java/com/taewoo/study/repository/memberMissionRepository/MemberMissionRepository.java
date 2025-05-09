package com.taewoo.study.repository.memberMissionRepository;

import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.domain.mapping.MemberMission;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);
}
