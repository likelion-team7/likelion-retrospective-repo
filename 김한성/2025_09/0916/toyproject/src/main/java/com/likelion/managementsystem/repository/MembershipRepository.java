package com.likelion.managementsystem.repository;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;
import com.likelion.managementsystem.domain.MemberShipType;

public interface MembershipRepository {
    void addMembership(MemberShip type, Member member);
}
