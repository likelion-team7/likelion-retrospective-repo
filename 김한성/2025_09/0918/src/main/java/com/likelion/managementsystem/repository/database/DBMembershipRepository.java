package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;
import com.likelion.managementsystem.domain.MemberShipType;
import com.likelion.managementsystem.repository.MembershipRepository;
import com.likelion.managementsystem.repository.database.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBMembershipRepository implements MembershipRepository {

    @Override
    public void addMembership(MemberShip memberShip, Member member) {
        String sql = "insert into membership (type, start_date, end_date, remaining_session, member_id) values (?,?,?,?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, memberShip.getType().name());
            ps.setDate(2, java.sql.Date.valueOf(memberShip.getStartDate()));   // LocalDate → SQL Date
            ps.setDate(3, memberShip.getEndDate() != null ? java.sql.Date.valueOf(memberShip.getEndDate()) : null);
            if (memberShip.getRemainingSessions() != null) {
                ps.setInt(4, memberShip.getRemainingSessions());
            } else {
                ps.setNull(4, java.sql.Types.INTEGER);
            }
            ps.setLong(5, member.getId());

            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public MemberShip getMembership(Long id) {
        MemberShip membership = null;
        String sql = "select * from membership where member_id = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                membership = new MemberShip();
                init(membership, rs);
            }
            return membership;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return membership;
    }

    @Override
    public void deleteByMemberId(Long memberId) {
        String sql = "delete from membership where member_id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, memberId);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateSession(MemberShip memberShip) {
        String sql = "update membership set remaining_session = ? where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, memberShip.getRemainingSessions());
            ps.setLong(2, memberShip.getId());
            ps.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private static void init(MemberShip membership, ResultSet rs) throws SQLException {
        membership.setId(rs.getLong("id"));
        membership.setType(MemberShipType.valueOf(rs.getString("type").toUpperCase()));
        membership.setStartDate(rs.getDate("start_date").toLocalDate());
        membership.setEndDate(rs.getDate("end_date").toLocalDate());
        membership.setRemainingSessions(rs.getInt("remaining_session"));
        membership.setMemberId(rs.getLong("member_id"));
    }
}
