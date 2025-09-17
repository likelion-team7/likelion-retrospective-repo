package member.DAO;

public class MemberDAOTest {
    public static void main(String[] args) {
        MemberDAO dao = new MemberDAO();

        int result = dao.insertMember("jeong", "jeong1234", "jeong1234@gmail.com");
    }
}
