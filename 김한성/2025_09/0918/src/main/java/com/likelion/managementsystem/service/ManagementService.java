package com.likelion.managementsystem.service;


import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;
import com.likelion.managementsystem.domain.MemberShipType;
import com.likelion.managementsystem.domain.Visit;
import com.likelion.managementsystem.repository.MemberRepository;
import com.likelion.managementsystem.repository.MembershipRepository;
import com.likelion.managementsystem.repository.VisitRepository;

import java.time.LocalDate;
import java.util.List;

public class ManagementService {

    private final MemberRepository memberRepository;
    private final VisitRepository visitRepository;
    private final MembershipRepository membershipRepository;

    public ManagementService(MemberRepository repository, VisitRepository visitRepository, MembershipRepository membershipRepository) {
        this.memberRepository = repository;
        this.visitRepository = visitRepository;
        this.membershipRepository = membershipRepository;
    }


    public void createVisitor(String name, String phoneNumber) {
        visitRepository.save(name, phoneNumber);
        System.out.println("[일일이용] - " + name + ", " + phoneNumber);
    }

    public List<Visit> getAllVisits() {
        return visitRepository.findAll();
    }

    public void createMemberShip(String name, String phoneNumber, MemberShipType type) {
        Member byPhoneNumber = memberRepository.findByPhoneNumber(phoneNumber);

        if (byPhoneNumber != null) {
            System.out.println("[오류] 이미 존재하는 회원 입니다.");
            return;
        }

        Member savedMember = memberRepository.saveAndReturnMember(new Member(name, phoneNumber));
        MemberShip memberShip = MemberShip.create(savedMember, type, LocalDate.now());
        membershipRepository.addMembership(memberShip, savedMember);
        System.out.println("회원권 : " + memberShip.getType() + ", 가격 : " + printPrice(memberShip) + ", 사용기한 : " + memberShip.getEndDate());
    }

    public boolean duplicatedPhoneNumberCheck(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber);
        return member != null;
    }

    /**
     * 전화 번호를 입력 받아서 회원권 / 회수권 에 따라 차감 또는 사용
     */
    public void useMemberShip(String phoneNumber) {
        if (!memberShipCheck(phoneNumber)) {
            System.out.println("[오류] 가입되지 않은 회원");
            return;
        }

        Member findMember = memberRepository.findByPhoneNumber(phoneNumber);
        MemberShip membership = membershipRepository.getMembership(findMember.getId());

        //회수권
        if(membership.isActive(LocalDate.now())) {
            if (membership.getType().isSessionBased()) {
                if (membership.getRemainingSessions() <= 0) {
                    System.out.println("[회수권 소진]");
                }else{
                    // 회수권 감소
                    membership.consumeOne();
                    membershipRepository.updateSession(membership);

                    System.out.println("[회수권 사용] : 남은 횟수 -> " + membership.getRemainingSessions());
                }
            }
        // 회원권
        } else if (membership.getType().name().endsWith("MONTH")) {
            System.out.println("[회원권 사용] : 사용 기한 -> " + membership.getEndDate());
        }
    }

    public void deleteMember(String phoneNumber) {
        Member member = memberRepository.findByPhoneNumber(phoneNumber);
        // membership 삭제 -> member 삭제
        membershipRepository.deleteByMemberId(member.getId());
        memberRepository.delete(member.getId());
        System.out.println(member.getName() + ", " + member.getPhoneNumber());
    }

    // enum
    private int printPrice(MemberShip memberShip) {
        int price = 0;
        switch (memberShip.getType()) {
            case ONE_MONTH:
                price = 130000;
                break;
            case THREE_MONTH:
                price = 300000;
                break;
            case TEN_SESSION:
                price = 170000;
                break;
            case THIRTY_SESSION:
                price = 300000;
                break;
        }
        return price;
    }

    private boolean memberShipCheck(String phoneNumber) {
        Member findMember = memberRepository.findByPhoneNumber(phoneNumber);
        return findMember != null;
    }

}
