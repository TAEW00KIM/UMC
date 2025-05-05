package com.taewoo.study.repository.memberMissionRepository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taewoo.study.domain.QMission;
import com.taewoo.study.domain.QStore;
import com.taewoo.study.domain.enums.MissionStatus;
import com.taewoo.study.domain.mapping.MemberMission;
import com.taewoo.study.domain.mapping.QMemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberMissionRepositoryImpl implements MemberMissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<MemberMission> findCompletedMissionByMember(Long memberId, Long cursorId, int limit) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        return jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin()
                .join(mission.store, store).fetchJoin()
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.COMPLETE),
                        cursorId != null ? memberMission.id.lt(cursorId) : null
                )
                .orderBy(memberMission.id.desc())
                .limit(limit)
                .fetch();
    }

    @Override
    public List<MemberMission> findChallengingMissionByMember(Long memberId, Long cursorId, int limit) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QMemberMission memberMission = QMemberMission.memberMission;

        return jpaQueryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission).fetchJoin()
                .join(mission.store, store).fetchJoin()
                .where(
                        memberMission.member.id.eq(memberId),
                        memberMission.status.eq(MissionStatus.CHALLENGING),
                        cursorId != null ? memberMission.id.lt(cursorId) : null
                )
                .orderBy(memberMission.id.desc())
                .limit(limit)
                .fetch();
    }
}
