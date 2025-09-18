package com.likelion.managementsystem.repository.database;

import com.likelion.managementsystem.domain.Visit;
import com.likelion.managementsystem.repository.VisitRepository;
import com.likelion.managementsystem.repository.database.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBVisitRepository implements VisitRepository {

    @Override
    public void save(String name, String phoneNumber) {
        String sql = "INSERT INTO visitors (name, phone) VALUES (?, ?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, name);
            ps.setString(2, phoneNumber);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Visit> findAll() {
        List<Visit> visits = new ArrayList<>();
        String sql = "SELECT id,name,phone,visited_at FROM visitors";

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                Visit visit = new Visit();
                visit.setId(rs.getLong(1));
                visit.setName(rs.getString(2));
                visit.setPhone(rs.getString(3));
                visit.setVisitedAt(rs.getTimestamp(4).toLocalDateTime());

                visits.add(visit);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            DBUtil.close(conn, ps, rs);
        }

        return visits;

    }
}
