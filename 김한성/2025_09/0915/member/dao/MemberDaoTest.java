package member.dao;

import member.dao.dto.MemberDto;

import java.util.List;

public class MemberDaoTest {
    public static void main(String[] args) {
        MemberDao dao = new MemberDao();
//        dao.insertMember("kim", "123", "123@124");

        List<MemberDto> memberDtos = dao.selectAllMembers();
        for (MemberDto memberDto : memberDtos) {
            System.out.println(memberDto);
        }

        dao.updateMember(1, "new-Name", "1234");


    }
}
