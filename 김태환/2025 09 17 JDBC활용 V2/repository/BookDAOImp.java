package net.likelion.repository;

import net.likelion.common.DBUtil;
import net.likelion.domain.dto.BookDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImp implements BookDAO {

    //책 추가
    @Override
    public boolean insert(BookDTO book) {
        boolean result = false;
        String sql = "insert into books (isbn,title,author) values (?,?,?)";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3,book.getAuthor());

            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }
        }catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("오류: 입력한 ISBN은 이미 존재합니다.");
            } else {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    //책 업데이트
    @Override
    public boolean update(BookDTO book) {
        boolean result = false;
        String sql = "update books set isbn=?,title=?,author=? where id=?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setString(1, book.getIsbn());
            ps.setString(2, book.getTitle());
            ps.setString(3,book.getAuthor());
            ps.setInt(4, book.getId());

            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }
        }catch (Exception e) {
            if (e.getMessage().contains("Duplicate entry")) {
                System.out.println("오류: 입력한 ISBN은 이미 다른 도서에 사용 중입니다.");
            } else {
                System.out.println(e.getMessage());
            }
        }
        return result;
    }

    //책 삭제
    @Override
    public boolean delete(BookDTO book) {
        boolean result = false;
        String sql = "delete from books where id =?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ) {
            ps.setInt(1, book.getId());

            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }
        }catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    //전체 책 조회
    @Override
    public List<BookDTO> findAll() {
        List<BookDTO> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from books";

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                bookList.add(resultSetToBook(rs));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return bookList;
    }

    // 제목 조회
    @Override
    public List<BookDTO> findByTitle(String title) {
        List<BookDTO> bookList = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from books where title like ?";

        try{
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);

            ps.setString(1,"%"+title+"%");
            rs = ps.executeQuery();

            while(rs.next()){
                bookList.add(resultSetToBook(rs));
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return bookList;
    }

    private BookDTO resultSetToBook(ResultSet rs) throws SQLException {
        BookDTO book = new BookDTO();

        book.setId(rs.getInt("id"));
        book.setIsbn(rs.getString("isbn"));
        book.setTitle(rs.getString("title"));
        book.setAuthor(rs.getString("author"));

        return book;
    }

}
