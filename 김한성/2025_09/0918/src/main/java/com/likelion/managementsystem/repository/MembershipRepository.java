package com.likelion.managementsystem.repository;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;

public interface MembershipRepository {

    void addMembership(MemberShip type, Member member);
    MemberShip getMembership(Long id);
    void deleteByMemberId(Long memberId);
    void updateSession(MemberShip memberShip);
}
