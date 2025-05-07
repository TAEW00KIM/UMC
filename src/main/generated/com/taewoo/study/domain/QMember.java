package com.taewoo.study.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QMember is a Querydsl query type for Member
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QMember extends EntityPathBase<Member> {

    private static final long serialVersionUID = -1268513315L;

    public static final QMember member = new QMember("member1");

    public final com.taewoo.study.domain.common.QBaseEntity _super = new com.taewoo.study.domain.common.QBaseEntity(this);

    public final StringPath address = createString("address");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath email = createString("email");

    public final EnumPath<com.taewoo.study.domain.enums.Gender> gender = createEnum("gender", com.taewoo.study.domain.enums.Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DatePath<java.time.LocalDate> inactiveDate = createDate("inactiveDate", java.time.LocalDate.class);

    public final ListPath<com.taewoo.study.domain.mapping.MemberAgree, com.taewoo.study.domain.mapping.QMemberAgree> memberAgreeList = this.<com.taewoo.study.domain.mapping.MemberAgree, com.taewoo.study.domain.mapping.QMemberAgree>createList("memberAgreeList", com.taewoo.study.domain.mapping.MemberAgree.class, com.taewoo.study.domain.mapping.QMemberAgree.class, PathInits.DIRECT2);

    public final ListPath<com.taewoo.study.domain.mapping.MemberMission, com.taewoo.study.domain.mapping.QMemberMission> memberMissionList = this.<com.taewoo.study.domain.mapping.MemberMission, com.taewoo.study.domain.mapping.QMemberMission>createList("memberMissionList", com.taewoo.study.domain.mapping.MemberMission.class, com.taewoo.study.domain.mapping.QMemberMission.class, PathInits.DIRECT2);

    public final ListPath<com.taewoo.study.domain.mapping.MemberPrefer, com.taewoo.study.domain.mapping.QMemberPrefer> memberPreferList = this.<com.taewoo.study.domain.mapping.MemberPrefer, com.taewoo.study.domain.mapping.QMemberPrefer>createList("memberPreferList", com.taewoo.study.domain.mapping.MemberPrefer.class, com.taewoo.study.domain.mapping.QMemberPrefer.class, PathInits.DIRECT2);

    public final EnumPath<com.taewoo.study.domain.enums.MemberStatus> memberStatus = createEnum("memberStatus", com.taewoo.study.domain.enums.MemberStatus.class);

    public final StringPath name = createString("name");

    public final StringPath phone = createString("phone");

    public final NumberPath<Integer> point = createNumber("point", Integer.class);

    public final ListPath<Review, QReview> reviewList = this.<Review, QReview>createList("reviewList", Review.class, QReview.class, PathInits.DIRECT2);

    public final EnumPath<com.taewoo.study.domain.enums.SocialType> socialType = createEnum("socialType", com.taewoo.study.domain.enums.SocialType.class);

    public final StringPath specAddress = createString("specAddress");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> updatedAt = _super.updatedAt;

    public QMember(String variable) {
        super(Member.class, forVariable(variable));
    }

    public QMember(Path<? extends Member> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMember(PathMetadata metadata) {
        super(Member.class, metadata);
    }

}

