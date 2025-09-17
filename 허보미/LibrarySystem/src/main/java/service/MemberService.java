package service;

import domain.Member;
import java.util.List;

public interface MemberService {
    int register(String username, String password, String role);
    Member login(String username, String password);
    List<Member> findAll();
    boolean findUserName(String username);
}
