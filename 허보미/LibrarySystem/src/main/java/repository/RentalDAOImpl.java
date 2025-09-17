package repository;

import common.DBUtil;
import domain.Rental;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentalDAOImpl implements RentalDAO {

    @Override
    public long insert(long bookId, long memberId, LocalDate rentedAt, LocalDate dueDate) {
        String sql = "INSERT INTO rental (book_id, member_id, rented_at, due_date) VALUES (?,?,?,?)";
        try (Connection c = DBUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setLong(1, bookId);
            ps.setLong(2, memberId);
            ps.setDate(3, Date.valueOf(rentedAt));
            ps.setDate(4, Date.valueOf(dueDate));
            int n = ps.executeUpdate();
            if (n > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) return rs.getLong(1);
                }
            }
            return 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public boolean returnBook(long rentalId, LocalDate returnedAt) {
        String sql = "UPDATE rental SET returned_at=? WHERE id=? AND returned_at IS NULL";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setDate(1, Date.valueOf(returnedAt));
            ps.setLong(2, rentalId);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public Optional<Rental> findActiveByBook(long bookId) {
        String sql = "SELECT id, book_id, member_id, rented_at, due_date, returned_at " +
                "FROM rental WHERE book_id=? AND returned_at IS NULL ORDER BY id DESC LIMIT 1";
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, bookId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    @Override
    public List<Rental> findByMember(long memberId) {
        String sql = "SELECT id, book_id, member_id, rented_at, due_date, returned_at " +
                "FROM rental WHERE member_id=? ORDER BY id DESC";
        List<Rental> list = new ArrayList<>();
        try (Connection c = DBUtil.getConnection(); PreparedStatement ps = c.prepareStatement(sql)) {
            ps.setLong(1, memberId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
            }
            return list;
        } catch (SQLException e) { throw new RuntimeException(e); }
    }

    private Rental map(ResultSet rs) throws SQLException {
        Date ret = rs.getDate("returned_at");
        return new Rental(
                rs.getLong("id"),
                rs.getLong("member_id"),
                rs.getLong("book_id"),
                rs.getDate("rented_at").toLocalDate(),
                rs.getDate("due_date").toLocalDate(),
                ret == null ? null : ret.toLocalDate()
        );
    }
}
