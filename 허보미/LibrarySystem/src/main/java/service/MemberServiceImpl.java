package service;

import domain.Member;
import repository.MemberDAO;

import java.util.List;

public class MemberServiceImpl implements MemberService {
    private final MemberDAO memberDAO;
    public MemberServiceImpl(MemberDAO memberDAO) { this.memberDAO = memberDAO; }

    @Override public int register(String username, String password, String role) {
        return memberDAO.insert(new Member(username, password, role));
    }
    @Override public Member login(String username, String password) {
        return memberDAO.authenticate(username, password);
    }
    @Override public List<Member> findAll() { return memberDAO.findAll(); }

    @Override public boolean findUserName(String username){return memberDAO.findByName(username);}
}
