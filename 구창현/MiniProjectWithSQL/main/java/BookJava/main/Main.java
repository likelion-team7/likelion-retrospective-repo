package BookJava.main.Mainpage;

import BookJava.main.adminpage.Adminpage;
import Repository.BookDAO;
import Repository.BookDAOImpl;
import Sql.DTO.BookDTO;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BookDAO bookDAO = new BookDAOImpl();
        Adminpage adminpage = new Adminpage();

        while (true) {
            System.out.println("========================\n");
            System.out.println("=====원하시는 기능을 선택해 주세요=====\n");
            System.out.println("========================\n");
            System.out.println("1. 도서 검색");
            System.out.println("2. 도서 전체보기");
            System.out.println("3. 관리자 페이지");
            System.out.println("0. 종료");
            int intinput = -1;
            try { //메뉴선택 예외처리
                intinput = scanner.nextInt();
                scanner.nextLine();
            }catch (Exception e){
                System.out.println("메뉴에있는 숫자를 선택해주세요");
                scanner.nextLine();
            }
            if (intinput > 3) {
                System.out.println();
                System.out.println("잘못된 입력입니다.");
            }

            if (intinput == 0) {
                System.out.println("프로그램을 종료합니다");
                break;
            }

            switch (intinput) {
                case 1:
                    System.out.println("========================\n");
                    System.out.println("찾으시는 도서제목을 입력해주세요\n");
                    System.out.println("========================\n");
                    String name = null;
                    name = scanner.nextLine();
                    if (name.isEmpty()) {
                        System.out.println("공백값을 입력하셨습니다. 다시 입력해주세요");
                        break;
                    }
                    System.out.println(bookDAO.getBooksByName(name));

                    break;

                case 2:
                    System.out.println("========================\n");
                    System.out.println("도서 전체보기\n");
                    System.out.println("========================\n");
                    System.out.println("===결과를 출력합니다===\n");

                    System.out.println(bookDAO.getAllBooks());

                    break;

                case 3:
                    adminpage.Adminpage();
            }
        }
    }
}

