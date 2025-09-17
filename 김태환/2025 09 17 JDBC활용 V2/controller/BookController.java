package net.likelion.controller;

import net.likelion.domain.dto.BookDTO;
import net.likelion.repository.BookDAOImp;
import net.likelion.service.BookService;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class BookController {

    private final BookService bookService;
    private final Scanner scanner;

    // V1 호환을 위한 생성자: Scanner만 인자로 받음
    public BookController(Scanner scanner) {
        this.bookService = new BookService(new BookDAOImp());
        this.scanner = scanner;
    }

    // V2 호환을 위한 생성자: BookService와 Scanner를 인자로 받음
    public BookController(BookService bookService, Scanner scanner) {
        this.bookService = bookService;
        this.scanner = scanner;
    }

    public void addBook() {
        System.out.print("도서 제목: ");
        scanner.nextLine();
        String title = scanner.nextLine();
        System.out.print("도서 저자: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();

        BookDTO book = new BookDTO();
        book.setTitle(title);
        book.setAuthor(author);
        book.setIsbn(isbn);

        boolean isAdded = bookService.addBook(book);
        if (isAdded) {
            System.out.println("도서가 성공적으로 추가되었습니다.");
        } else {
            System.out.println("도서 추가에 실패했습니다.");
        }
    }

    public void getAllBooks() {
        System.out.println("\n--- 전체 도서 목록 ---");
        List<BookDTO> books = bookService.getAllBooks();
        if (books.isEmpty()) {
            System.out.println("등록된 도서가 없습니다.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void getBooksByTitle() {
        scanner.nextLine();
        System.out.print("검색할 도서 제목: ");
        String title = scanner.nextLine();

        System.out.println("\n--- 제목 검색 결과 ---");
        List<BookDTO> books = bookService.getBooksByTitle(title);
        if (books.isEmpty()) {
            System.out.println("검색 결과가 없습니다.");
        } else {
            books.forEach(System.out::println);
        }
    }

    public void updateBook() {
        try {
            System.out.print("업데이트할 도서 ID를 입력하세요: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("새로운 도서 제목: ");
            String title = scanner.nextLine();
            System.out.print("새로운 도서 저자: ");
            String author = scanner.nextLine();
            System.out.print("새로운 ISBN: ");
            String isbn = scanner.nextLine();

            BookDTO book = new BookDTO();
            book.setId(id);
            book.setTitle(title);
            book.setAuthor(author);
            book.setIsbn(isbn);

            boolean isUpdated = bookService.updateBook(book);
            if (isUpdated) {
                System.out.println("도서가 성공적으로 업데이트되었습니다.");
            } else {
                System.out.println("도서 업데이트에 실패했습니다. ID를 다시 확인해주세요.");
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. ID는 숫자를 입력해주세요.");
            scanner.nextLine();
        }
    }

    public void deleteBook() {
        try {
            System.out.print("삭제할 도서 ID를 입력하세요: ");
            int id = scanner.nextInt();

            BookDTO book = new BookDTO();
            book.setId(id);

            boolean isDeleted = bookService.deleteBook(book);
            if (isDeleted) {
                System.out.println("도서가 성공적으로 삭제되었습니다.");
            } else {
                System.out.println("도서 삭제에 실패했습니다. ID를 다시 확인해주세요.");
            }
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. ID는 숫자를 입력해주세요.");
            scanner.nextLine();
        }
    }
}
