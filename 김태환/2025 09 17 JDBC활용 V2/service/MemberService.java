package net.likelion.service;

import net.likelion.domain.dto.MemberDTO;
import net.likelion.repository.MemberDAOImp;

public class MemberService {
    private final MemberDAOImp memberDAO;

    public MemberService(MemberDAOImp memberDAO) {
        this.memberDAO = memberDAO;
    }

    public MemberDTO login(String username, String password) {
        return memberDAO.login(username, password);
    }

    public boolean registerMember(MemberDTO member) {
        return memberDAO.insertMember(member);
    }
}
