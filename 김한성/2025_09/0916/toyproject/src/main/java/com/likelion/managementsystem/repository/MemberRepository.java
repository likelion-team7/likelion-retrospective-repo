package com.likelion.managementsystem.repository;


import com.likelion.managementsystem.domain.Member;

import java.util.List;

public interface MemberRepository {

    void save(Member member);

    Member saveAndReturnMember(Member member);

    void delete(Long id);

    Member findByPhoneNumber(String phoneNumber);

    List<Member> findAll();

}
