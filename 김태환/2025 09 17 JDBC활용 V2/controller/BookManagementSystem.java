package net.likelion.controller;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManagementSystem {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookController bookController = new BookController(scanner);

    public static void main(String[] args) {
        System.out.println("도서 관리 시스템에 오신 것을 환영합니다.");
        while (true) {
            printMenu();
            int choice = getUserChoice();

            switch (choice) {
                case 1:
                    bookController.getAllBooks();
                    break;
                case 2:
                    bookController.getBooksByTitle();
                    break;
                case 3:
                    bookController.addBook();
                    break;
                case 4:
                    bookController.updateBook();
                    break;
                case 5:
                    bookController.deleteBook();
                    break;
                case 6:
                    System.out.println("시스템을 종료합니다.");
                    return;
                default:
                    System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- 메뉴 ---");
        System.out.println("1. 전체 도서 검색");
        System.out.println("2. 제목으로 도서 검색");
        System.out.println("3. 도서 추가");
        System.out.println("4. 도서 정보 업데이트");
        System.out.println("5. 도서 삭제");
        System.out.println("6. 종료");
        System.out.print("메뉴 번호를 입력하세요: ");
    }

    private static int getUserChoice() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
}
