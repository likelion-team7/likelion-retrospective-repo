package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Member;
import com.likelion.managementsystem.repository.MemberRepository;
import com.likelion.managementsystem.repository.database.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBMemberRepository implements MemberRepository {
    @Override
    public Member saveAndReturnMember(Member member) {
        Member savedMember = null;
        String sql = "insert into members (name, phone_number) values (?, ?)";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getPhoneNumber());
            ps.executeUpdate();
            savedMember = new Member(member.getName(), member.getPhoneNumber());
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                savedMember.setId(rs.getLong(1));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        return savedMember;
    }

    @Override
    public void save(Member member) {
        String sql = "insert into members (name, phone_number) values (?, ?)";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, member.getName());
            ps.setString(2, member.getPhoneNumber());
            ps.executeUpdate();

            Member m1 = new Member(member.getName(), member.getPhoneNumber());

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Long id) {
        String sql = "delete from members where id = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public Member findByPhoneNumber(String phoneNumber) {
        Member member = null;
        String sql = "select * from members where phone_number = ?";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1, phoneNumber);
            rs = ps.executeQuery();

            if (rs.next()) {
                member = new Member(rs.getLong("id"),rs.getString("name"), rs.getString("phone_number"));
            }

            return member;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }
        return null;
    }

    @Override
    public List<Member> findAll() {
        List<Member> members = new ArrayList<>();
        String sql = "select * from members";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                members.add(new Member(rs.getLong("id"),rs.getString("name"), rs.getString("phone_number")));
            }

            return members;

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        return members;
    }
}
