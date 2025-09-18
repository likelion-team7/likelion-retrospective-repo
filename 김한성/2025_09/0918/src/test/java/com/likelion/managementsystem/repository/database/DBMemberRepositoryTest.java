package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBMemberRepositoryTest {

    MemberRepository memberRepository = new DBMemberRepository();

    @Test
    void save() {
        Member member1 = new Member("kim", "010-1111-1111");
        Member member2 = new Member("park", "010-2222-2222");
        Member member3 = new Member("lee", "010-3333-3333");
        Member member4 = new Member("seo", "010-4444-4444");
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);
        memberRepository.save(member4);
    }

    @Test
    void delete() {
        memberRepository.delete(1L);
    }

    @Test
    void findByPhoneNumber() {
        Member findMember = memberRepository.findByPhoneNumber(("010-1111-1111"));
        Assertions.assertThat(findMember.getName()).isEqualTo("kim");
    }

    @Test
    void findAll() {
        List<Member> members = memberRepository.findAll();
        Assertions.assertThat(members.size()).isEqualTo(4);
    }
}