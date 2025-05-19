package com.taewoo.study.repository.memberMissionRepository;

import com.taewoo.study.domain.Member;
import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.domain.mapping.MemberMission;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMissionRepositoryCustom {
    boolean existsByMemberIdAndMissionIdAndStatus(Long memberId, Long missionId, MissionStatus status);

    @Query("select mm from MemberMission mm " +
            "join fetch mm.mission m " +
            "join fetch m.store s " +
            "where mm.member = :member and mm.status = :status")
    Page<MemberMission> findAllByMemberAndStatusWithDetails(@Param("member")Member member,
                                                            @Param("status") MissionStatus status,
                                                            Pageable pageable);
}
