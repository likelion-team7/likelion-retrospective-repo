package library.repository;

import library.common.DBUtil;
import library.dto.BookDTO;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    @Override
    public boolean addBook(BookDTO book) {
        boolean result = false;
        String sql = "insert into books (title, isbn, author) values (?,?,?)";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3,book.getAuthor());

            int count = ps.executeUpdate();
            if(count > 0) {
                result = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return result;
    }

    @Override
    public boolean updateBook(BookDTO book) {
        boolean result = false;
        String sql = "update books set title=?, isbn=?, author=? where id =?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setString(3,book.getAuthor());
            ps.setInt(4, book.getId());
            ps.executeUpdate();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }


        return result;
    }

    @Override
    public boolean deleteBook(BookDTO book) {
        boolean result = false;
        String sql = "delete from books where id =?";
        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ){
            ps.setInt(1, book.getId());
            int count = ps.executeUpdate();
            if(count > 0) {
                result = true;
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return result;
    }

    @Override
    public List<BookDTO> getBooks() {
        List<BookDTO> books = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs  = null;
        String sql = "select * from books";

        try {
            conn = DBUtil.getConnection();
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next()){
                books.add(resultSetToBook(rs));
            }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(conn,ps,rs);
        }

        return books;
    }

    @Override
    public BookDTO getBook(String title) {

        BookDTO book = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "select * from books where title = ?";

        try{
           conn = DBUtil.getConnection();
           ps = conn.prepareStatement(sql);
           ps.setString(1, title);
           rs = ps.executeQuery();

           if (rs.next()){
               book = resultSetToBook(rs);

           }
        }catch (Exception e){
            System.out.println(e.getMessage());
        }finally {
            DBUtil.close(conn,ps,rs);
        }
        return book;
    }
    private BookDTO resultSetToBook(ResultSet rs) throws SQLException {
        BookDTO book = new BookDTO();

        book.setId(rs.getInt("id"));
        book.setTitle(rs.getString("title"));
        book.setIsbn(rs.getString("isbn"));
        book.setAuthor(rs.getString("author"));
        return book;
    }
}
