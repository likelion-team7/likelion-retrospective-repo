package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;
import com.likelion.managementsystem.domain.MemberShipType;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class DBMembershipRepositoryTest {

    DBMembershipRepository membershipRepository = new DBMembershipRepository();
    DBMemberRepository memberRepository = new DBMemberRepository();

    @Test
    void addMembership() {
//        Member findMember = memberRepository.findByPhoneNumber("010-1122-1122");
        Member findMember = memberRepository.findByPhoneNumber("010-1111-1111");
        MemberShip memberShip = MemberShip.create(findMember, MemberShipType.TEN_SESSION, LocalDate.now());
        membershipRepository.addMembership(memberShip, findMember);

    }
}