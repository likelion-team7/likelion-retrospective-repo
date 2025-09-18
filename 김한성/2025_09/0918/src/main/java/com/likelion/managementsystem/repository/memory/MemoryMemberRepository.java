package com.likelion.managementsystem.repository.memory;


import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.repository.MemberRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MemoryMemberRepository implements MemberRepository {

    private static final Map<Long, Member> memberStore = new HashMap<>();
    private static Long sequance = 0L;

    @Override
    public void save(Member member) {
        member.setId(++sequance);
        memberStore.put(member.getId(), member);
    }

    @Override
    public Member saveAndReturnMember(Member member) {
        return null;
    }

    @Override
    public void delete(Long id) {
        memberStore.remove(id);
    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        return findAll().stream()
                .filter(m -> m.getPhoneNumber().equals(phoneNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(memberStore.values());
    }

}
