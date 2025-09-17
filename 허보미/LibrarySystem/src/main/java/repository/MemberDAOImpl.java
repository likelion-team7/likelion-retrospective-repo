package repository;

import common.DBUtil;
import domain.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MemberDAOImpl implements MemberDAO {

    @Override
    public int insert(Member member){
        String sql = "INSERT INTO member (username,password,role) VALUES (?,?,?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, member.getUsername());
            ps.setString(2, member.getPassword());
            ps.setString(3, member.getRole());
            return ps.executeUpdate();
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Member authenticate(String username, String password){
        String sql = "SELECT id, username, password, role, created_at FROM member WHERE username=? AND password=?";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()){
                if (rs.next()){
                    return new Member(
                            rs.getLong("id"),
                            rs.getString("username"),
                            rs.getString("password"),
                            rs.getString("role"),
                            rs.getTimestamp("created_at").toLocalDateTime()
                    );
                }
            }
            return null;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Member> findAll() {
        String sql = "SELECT id, username, password, role, created_at FROM member ORDER BY id";
        List<Member> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Member(
                        rs.getLong("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"),
                        rs.getTimestamp("created_at").toLocalDateTime()
                ));
            }
            return list;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public boolean findByName(String username){
        String sql = "SELECT COUNT(*) FROM member WHERE username=?";

        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1); // COUNT(*) 결과값
                    return count > 0;        // 있으면 true, 없으면 false
                }
            }
            return false; // 결과가 없으면 false
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
