package member.dao;

import member.dao.dto.MemberDto;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static member.dao.util.DBUtil.*;

public class MemberDao {

    // insert
    public int insertMember(String name, String password, String email) {
        String sql = "insert into members(name,password,email) values(?,?,?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setString(3, email);
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //delete
    public void deleteMember(int id) {
        String sql = "delete from members where id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void updateMember(int id, String name, String password) {
        String sql = "update members set name = ?, password = ? where id = ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, password);
            ps.setInt(3, id);
            ps.execute();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    // select
    public List<MemberDto> selectAllMembers() {
        List<MemberDto> list = new ArrayList<>();
        String sql = "select id,name,password,email from members";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while( rs.next() ) {
                MemberDto member = new MemberDto();
                member.setId(rs.getInt("id"));
                member.setName(rs.getString("name"));
                member.setPassword(rs.getString("password"));
                member.setEmail(rs.getString("email"));
                list.add(member);
//                list.add(new MemberDto(Integer.parseInt(rs.getString(1)), rs.getString(2), rs.getString(3), rs.getString(4)));
            }

            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            close(conn, ps, rs);
        }
    }
}
