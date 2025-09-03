package day10;

import java.util.ArrayList;
import java.util.Scanner;

public class BookTest {
    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        Library library=new Library();
        while (true) {
            System.out.println("1.도서 추가          2.전체 도서 목록 조회");
            System.out.println("3.도서 검색          4.도서 대여");
            System.out.println("5.도서 반납          6.시스템 종료");
//            System.out.println("5.도서 반납          6.도서 제거");
//            System.out.println("7. 시스템 종료");
            String input=scanner.nextLine();
            int choice;
            choice=Integer.parseInt(input);

            switch (choice) {
                case 1:
                    System.out.println("제목입력후 저자를입력해주세요.");
                    System.out.println("도서추가: ");
                    String title=scanner.nextLine();
                    System.out.println("저자 입력:");
                    String author=scanner.nextLine();
                    library.addBook(author,title);
                    break;
                case 2:
                    library.listAllBooks();
                    break;
                case 3:
                    System.out.println("조회할 도서: ");
                    String searchBook=scanner.nextLine();
                    library.searchBook(searchBook);
                    System.out.println();
                    break;
                case 4:
                    System.out.println("대여할 도서:");
                    String rentBook = scanner.nextLine();
                    library.rentBook(rentBook);
                    System.out.println();
                    break;
                case 5:
                    System.out.println("반납할 도서: ");
                    String returnBook = scanner.nextLine();
                    library.returnBook(returnBook);
                    System.out.println();
                    break;
//                case 6:
//                    System.out.println("제거할 도서");
//                    String removeBook = scanner.nextLine();
//                    library.removeBook(Long.parseLong(removeBook));
//                    System.out.println();
                case 6:
                    System.out.println("프로그램 종료");
                    scanner.close();
                    return;
                default:
                    System.out.println("잘못된 숫자 선택 입력은1~6사이.");
            }
        }

  }
}
