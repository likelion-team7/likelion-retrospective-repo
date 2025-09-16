package member.DAO;

import lion.jdbc.DBUtil;
import member.DAO.DTO.MemberDTO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MemberDAO {

    // insert
    public int insertMember(String name, String password, String email){
        String sql = "insert into members(name, password, email) VALUES (?, ?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);

            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    // update
    public int updateMember(int id, String name, String password, String email) {
        String sql = "update members set name = ?, password = ?, email = ? where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            ps.setInt(4, id);

            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // delete
    public int deleteMember(int id) {
        String sql = "delete from members where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);


            return ps.executeUpdate();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // select
    public List<MemberDTO> selectAllMembers(String name, String password, String email) {
        List<MemberDTO> list = new ArrayList<>();
        String sql = "select * from members";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            // 조회 부분 추후 삽입

        }catch (Exception e){
            throw new RuntimeException(e);
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return list;
    }

}
