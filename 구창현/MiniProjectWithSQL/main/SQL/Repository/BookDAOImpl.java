package Repository;

import Sql.DTO.BookDTO;
import Util.DBUtil;

import java.awt.print.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BookDAOImpl implements BookDAO {
    //추가
    @Override
    public void addBook(BookDTO bookDTO) {
        boolean result = false;
        String sql = "INSERT into book (name, author) values (?,?)";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, bookDTO.getName());
            ps.setString(2, bookDTO.getAuthor());
            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //수정
    @Override
    public void updateBook(BookDTO bookDTO) {
        boolean result = false;
        String sql = "UPDATE book SET name = ?, author = ? where id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setString(1, bookDTO.getName());
            ps.setString(2, bookDTO.getAuthor());
            ps.setInt(3, bookDTO.getId());
            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //삭제
    @Override
    public void deleteBook(BookDTO bookDTO) {
        boolean result = false;
        String sql = "DELETE FROM book WHERE id = ?";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql)
        ) {
            ps.setInt(1, bookDTO.getId());
            int count = ps.executeUpdate();
            if (count > 0) {
                result = true;
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    //전체조회
    @Override
    public List<BookDTO> getAllBooks() {
        List<BookDTO> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
                ResultSet rs = ps.executeQuery();
        ) {
            while (rs.next()) {
                BookDTO book = new BookDTO(
                        rs.getInt("id"),       // id 컬럼
                        rs.getString("name"),  // name 컬럼
                        rs.getString("author") // author 컬럼
                );
                books.add(book);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return books;
    }

    @Override
    public List<BookDTO> getBooksByName(String name) {
        String sql = "SELECT * FROM book WHERE name LIKE ?";
        List<BookDTO> books = new ArrayList<>();

        try(
                Connection conn = DBUtil.getConnection();
                PreparedStatement ps = conn.prepareStatement(sql);
        ){
            ps.setString(1, "%" + name + "%"); //포함된 값을 찾을수 있게
            try (ResultSet rs = ps.executeQuery();){
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String bookTitle = rs.getString("name");
                    String author = rs.getString("author");
                    books.add(new BookDTO(id, bookTitle, author));
                }

            }
        }catch (Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return books;
    }
}
