package com.taewoo.study.repository.mission;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taewoo.study.domain.Mission;
import com.taewoo.study.domain.QMission;
import com.taewoo.study.domain.QRegion;
import com.taewoo.study.domain.QStore;
import com.taewoo.study.domain.mapping.QMemberMission;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Mission> findAvailableMissions(String regionName, Long memberId, Long cursorId, int limit) {
        QMission mission = QMission.mission;
        QStore store = QStore.store;
        QRegion region = QRegion.region;
        QMemberMission memberMission = QMemberMission.memberMission;

        return jpaQueryFactory
                .selectFrom(mission)
                .join(mission.store, store).fetchJoin()
                .join(store.region, region).fetchJoin()
                .where(
                        region.name.eq(regionName),
                        mission.deadline.gt(LocalDate.now()),
                        cursorId != null ? mission.id.lt(cursorId) : null,
                        mission.id.notIn(
                                jpaQueryFactory
                                        .select(memberMission.mission.id)
                                        .from(memberMission)
                                        .where(memberMission.member.id.eq(memberId))
                        )
                )
                .orderBy(mission.id.desc())
                .limit(limit)
                .fetch();
    }
}
