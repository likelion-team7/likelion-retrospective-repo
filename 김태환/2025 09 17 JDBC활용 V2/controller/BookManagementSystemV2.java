package net.likelion.controller;

import net.likelion.domain.dto.MemberDTO;
import net.likelion.repository.BookDAOImp;
import net.likelion.repository.MemberDAOImp;
import net.likelion.service.BookService;
import net.likelion.service.MemberService;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookManagementSystemV2 {

    private static final Scanner scanner = new Scanner(System.in);
    private static final BookDAOImp bookDAO = new BookDAOImp();
    private static final BookService bookService = new BookService(bookDAO);
    private static final MemberDAOImp memberDAO = new MemberDAOImp();
    private static final MemberService memberService = new MemberService(memberDAO);
    private static final BookController bookController = new BookController(bookService, scanner);

    private static MemberDTO loggedInUser = null;

    public static void main(String[] args) {
        System.out.println("도서 관리 시스템에 오신 것을 환영합니다.");

        while (true) {
            System.out.println("\n--- 로그인/회원가입 메뉴 ---");
            System.out.println("1. 로그인");
            System.out.println("2. 회원가입");
            System.out.println("3. 시스템 종료");
            System.out.print("선택: ");

            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        login();
                        if (loggedInUser != null) {
                            System.out.println("로그인 성공! (" + loggedInUser.getRole() + " 권한)");
                            startBookManagement();
                        } else {
                            System.out.println("로그인 실패: 아이디 또는 비밀번호가 잘못되었습니다.");
                        }
                        break;
                    case 2:
                        registerMember();
                        break;
                    case 3:
                        System.out.println("시스템을 종료합니다.");
                        return;
                    default:
                        System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.");
                }
            } catch (InputMismatchException e) {
                System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
                scanner.nextLine();
            }
        }
    }

    private static void login() {
        System.out.println("\n--- 로그인 ---");
        System.out.print("아이디: ");
        String username = scanner.next();
        System.out.print("비밀번호: ");
        String password = scanner.next();
        scanner.nextLine();

        loggedInUser = memberService.login(username, password);
    }

    private static void registerMember() {
        System.out.println("\n--- 회원가입 ---");
        System.out.print("아이디: ");
        String username = scanner.next();
        System.out.print("비밀번호: ");
        String password = scanner.next();
        scanner.nextLine();

        MemberDTO newMember = new MemberDTO();
        newMember.setUsername(username);
        newMember.setPassword(password);
        newMember.setRole("user");

        boolean isRegistered = memberService.registerMember(newMember);
        if (isRegistered) {
            System.out.println("회원가입 성공!");
        } else {
            System.out.println("회원가입 실패: 이미 존재하는 아이디입니다.");
        }
    }

    private static void startBookManagement() {
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
                    if ("admin".equals(loggedInUser.getRole())) {
                        bookController.addBook();
                    } else {
                        System.out.println("권한이 없습니다.");
                    }
                    break;
                case 4:
                    if ("admin".equals(loggedInUser.getRole())) {
                        bookController.updateBook();
                    } else {
                        System.out.println("권한이 없습니다.");
                    }
                    break;
                case 5:
                    if ("admin".equals(loggedInUser.getRole())) {
                        bookController.deleteBook();
                    } else {
                        System.out.println("권한이 없습니다.");
                    }
                    break;
                case 6:
                    System.out.println("로그아웃 합니다.");
                    loggedInUser = null;
                    return;
                case 7:
                    System.out.println("시스템을 종료합니다.");
                    System.exit(0);
                default:
                    System.out.println("잘못된 메뉴 번호입니다. 다시 선택해주세요.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- 메뉴 ---");
        System.out.println("1. 전체 도서 검색");
        System.out.println("2. 제목으로 도서 검색");

        if ("admin".equals(loggedInUser.getRole())) {
            System.out.println("3. 도서 추가");
            System.out.println("4. 도서 정보 업데이트");
            System.out.println("5. 도서 삭제");
        }
        System.out.println("6. 로그아웃");
        System.out.println("7. 종료");
        System.out.print("메뉴 번호를 입력하세요: ");
    }

    private static int getUserChoice() {
        try {
            int choice = scanner.nextInt();
            scanner.nextLine();
            return choice;
        } catch (InputMismatchException e) {
            System.out.println("잘못된 입력입니다. 숫자를 입력해주세요.");
            scanner.nextLine();
            return -1;
        }
    }
}
