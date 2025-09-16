package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.domain.MemberShip;
import com.likelion.managementsystem.domain.MemberShipType;
import com.likelion.managementsystem.repository.MembershipRepository;
import com.likelion.managementsystem.repository.database.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
}
