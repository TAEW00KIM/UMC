package com.taewoo.study.repository.memberRepository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.taewoo.study.domain.QMember;
import com.taewoo.study.web.dto.memberDto.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public MemberInfoDto findMemberInfoById(Long memberId) {
        QMember member = QMember.member;

        return jpaQueryFactory
                .select(Projections.constructor(MemberInfoDto.class,
                        member.id,
                        member.name,
                        member.email,
                        member.phone,
                        member.point
                ))
                .from(member)
                .where(member.id.eq(memberId))
                .fetchOne();
    }
}
