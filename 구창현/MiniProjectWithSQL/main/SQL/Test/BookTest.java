package Test;

import Repository.BookDAO;
import Repository.BookDAOImpl;
import Sql.DTO.BookDTO;

public class BookTest {
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();
        bookDAO.updateBook(new BookDTO(2,"초보자", "구창현현"));





    }
}
