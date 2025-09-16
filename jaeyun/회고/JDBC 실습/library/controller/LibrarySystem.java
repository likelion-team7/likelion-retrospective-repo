package library.controller;

import library.dto.BookDTO;
import library.repository.BookDAO;
import library.repository.BookDAOImpl;

import java.util.List;
import java.util.Scanner;

public class LibrarySystem {
    public static void main(String[] args) {
        BookDAO book = new BookDAOImpl();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== 도서 관리 시스템 =====");
            System.out.println("1. 책 추가");
            System.out.println("2. 전체 조회");
            System.out.println("3. 책 검색 (제목)");
            System.out.println("4. 책 수정");
            System.out.println("5. 책 삭제");
            System.out.println("6. 종료");
            System.out.print("메뉴 선택: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("제목: ");
                    String title = sc.nextLine();
                    System.out.print("ISBN: ");
                    String isbn = sc.nextLine();
                    System.out.print("저자: ");
                    String author = sc.nextLine();
                    book.addBook(new BookDTO(title, isbn, author));
                    System.out.println("책이 추가되었습니다.");
                    break;

                case 2:
                    List<BookDTO> books = book.getBooks();
                    System.out.println("\n전체 책 목록:");
                    books.forEach(System.out::println);
                    break;

                case 3:
                    System.out.print("검색할 제목: ");
                    String searchTitle = sc.nextLine();
                    BookDTO dto = book.getBook(searchTitle);
                    if (dto != null) {
                        System.out.println(dto);
                    } else {
                        System.out.println("해당 제목의 책이 없습니다.");
                    }
                    break;

                case 4:
                    System.out.print("수정할 책 제목: ");
                    String updateTitle = sc.nextLine();

                    BookDTO toUpdate = null;
                    for (BookDTO b : book.getBooks()) {
                        if (b.getTitle().equalsIgnoreCase(updateTitle)) {
                            toUpdate = b;
                            break;
                        }
                    }

                    if (toUpdate != null) {
                        System.out.print("새 제목: ");
                        toUpdate.setTitle(sc.nextLine());
                        System.out.print("새 ISBN: ");
                        toUpdate.setIsbn(sc.nextLine());
                        System.out.print("새 저자: ");
                        toUpdate.setAuthor(sc.nextLine());

                        boolean updated = book.updateBook(toUpdate);
                        System.out.println("수정 성공 여부: " + updated);
                    } else {
                        System.out.println("책 없음");
                    }
                    break;

                case 5:
                    System.out.print("삭제할 책 제목: ");
                    String deleteTitle = sc.nextLine();

                    BookDTO toDelete = null;
                    for (BookDTO b : book.getBooks()) {
                        if (b.getTitle().equalsIgnoreCase(deleteTitle)) {
                            toDelete = b;
                            break;
                        }
                    }

                    if (toDelete != null) {
                        boolean deleted = book.deleteBook(toDelete);
                        System.out.println("삭제 성공 여부: " + deleted);
                    } else {
                        System.out.println("책 없음");
                    }
                    break;

                case 6:
                    System.out.println("프로그램 종료");
                    sc.close();
                    return;

                default:
                    System.out.println("잘못된 입력입니다.");
            }
        }
    }
}

