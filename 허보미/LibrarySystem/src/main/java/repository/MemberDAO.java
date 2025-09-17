package repository;

import domain.Member;
import java.util.List;
import java.util.Optional;

public interface MemberDAO {
    //회원 등록
    int insert(Member member);
    //회원확인
    Member authenticate(String username, String password);
    //전체 회원 조회
    List<Member> findAll();
    //id 회원 조회
    boolean findByName(String username);
}
