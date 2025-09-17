package library.service;

import library.dto.BookDTO;
import library.repository.BookDAO;
import library.repository.BookDAOImpl;

import java.util.List;

public class BookTest {
    public static void main(String[] args) {
        BookDAO book = new BookDAOImpl();

        // 책 추가
        BookDTO book1 = new BookDTO("Clean Code", "978-0132350884", "Robert C. Martin");
        boolean add1 = book.addBook(book1);
        System.out.println("책 추가 성공 여부: " + add1);

        BookDTO book2 = new BookDTO("Effective Java", "978-0134685991", "Joshua Bloch");
        boolean add2 = book.addBook(book2);
        System.out.println("책 추가 성공 여부: " + add2);

        // 전체 조회
        List<BookDTO> books = book.getBooks();
        System.out.println("\n책 목록:");
        for (BookDTO b : books) {
            System.out.println(b);
        }

        // 특정 제목 조회
        // 수정
        // 삭제

    }
}
