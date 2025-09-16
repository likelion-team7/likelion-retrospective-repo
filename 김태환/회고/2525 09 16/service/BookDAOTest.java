package net.likelion.service;

import net.likelion.dto.BookDTO;
import net.likelion.repository.BookDAOImp;

import java.util.List;

public class BookDAOTest {

    public static void main(String[] args) {
        BookDAOImp bookDAO = new BookDAOImp();

       // 1. 책 추가 (INSERT) 테스트
//        System.out.println("--- 1. 책 추가 테스트 ---");
//        BookDTO newBook = new BookDTO();
//        newBook.setTitle("처음부터 제대로 배우는 스프링 부트");
//        newBook.setAuthor("마크헤클러");
//        newBook.setIsbn("1234567891234");
//
//        boolean insertResult = bookDAO.insert(newBook);
//        if (insertResult) {
//            System.out.println("책 추가 성공!");
//        } else {
//            System.out.println("책 추가 실패!");
//        }

        // 2. 전체 책 조회 (findAll) 테스트
//        System.out.println("\n--- 2. 전체 책 조회 테스트 ---");
//        List<BookDTO> allBooks = bookDAO.findAll();
//        allBooks.forEach(book -> System.out.println(book.toString()));

        // 3. 제목으로 조회 (findByTitle) 테스트
//        System.out.println("\n--- 3. 제목으로 조회 테스트 ---");
//        List<BookDTO> foundBooks = bookDAO.findByTitle("자바");
//        foundBooks.forEach(book -> System.out.println(book.toString()));

        // 4. 책 업데이트 (UPDATE) 테스트. id로 업데이트

//        System.out.println("\n--- 4. 책 업데이트 테스트 ---");
//        BookDTO updateBook = new BookDTO();
//        updateBook.setId(1);
//        updateBook.setTitle("자바의 정석 (개정판)");
//        updateBook.setAuthor("남궁성");
//        updateBook.setIsbn("9788956747124");
//
//        boolean updateResult = bookDAO.update(updateBook);
//        if (updateResult) {
//            System.out.println("책 업데이트 성공!");
//        } else {
//            System.out.println("책 업데이트 실패!");
//        }

       // 5. 책 삭제 (DELETE) 테스트
//        System.out.println("\n--- 5. 책 삭제 테스트 ---");
//        BookDTO deleteBook = new BookDTO();
//        deleteBook.setId(3); //
//
//        boolean deleteResult = bookDAO.delete(deleteBook);
//        if (deleteResult) {
//            System.out.println("책 삭제 성공!");
//        } else {
//            System.out.println("책 삭제 실패!");
//        }


    }
}
