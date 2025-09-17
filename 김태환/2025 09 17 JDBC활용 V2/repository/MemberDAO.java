package net.likelion.repository;

import net.likelion.domain.dto.MemberDTO;

public interface MemberDAO {
    // 회원 가입
    boolean insertMember(MemberDTO member);
    // 로그인
    MemberDTO login(String username, String password);
}
