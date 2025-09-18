package com.likelion.managementsystem.domain;


import java.time.LocalDate;

public class MemberShip {

    private Long id;
    private MemberShipType type;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer remainingSessions;
    private Long memberId;

    public MemberShip() {
    }

    public static MemberShip create(Member owner, MemberShipType type, LocalDate start) {
        MemberShip memberShip = new MemberShip();
        memberShip.memberId = owner.getId();
        memberShip.type = type;
        memberShip.startDate = start;
        memberShip.endDate = type.expireAt(start).orElse(null);
        memberShip.remainingSessions = type.isSessionBased() ? type.initialSessions() : null;
        return memberShip;
    }

    public boolean isActive(LocalDate today) {
        if (type.isSessionBased()) {
            return remainingSessions != null && !isExpired(today);
        }
        return isExpired(today);
    }

    public void consumeOne() {
        if (!type.isSessionBased()) return;
        if (remainingSessions == null || remainingSessions <= 0) {
            System.out.println("[회수권 소진]");
        } else {
            remainingSessions -= 1;
        }
    }

    private boolean isExpired(LocalDate today) {
        return endDate != null && today.isAfter(endDate);
    }




    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public MemberShipType getType() {
        return type;
    }

    public void setType(MemberShipType type) {
        this.type = type;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Integer getRemainingSessions() {
        return remainingSessions;
    }

    public void setRemainingSessions(Integer remainingSessions) {
        this.remainingSessions = remainingSessions;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }
}
