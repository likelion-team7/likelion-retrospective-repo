package app;
import domain.Member;
import repository.*;
import service.*;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BookDAO bookDAO = new BookDAOImpl();
        MemberDAO memberDAO = new MemberDAOImpl();
        RentalDAO rentalDAO = new RentalDAOImpl();

        BookService bookService = new BookServiceImpl(bookDAO);
        MemberService memberService = new MemberServiceImpl(memberDAO);
        RentalService rentalService = new RentalServiceImpl(bookDAO, rentalDAO);

        LoginConsole loginConsole = new LoginConsole(sc, memberService);
        SignUpConsole signUpConsole = new SignUpConsole(sc, memberService);
        AdminConsole adminConsole = new AdminConsole(sc, bookService, memberService);
        UserConsole userConsole = new UserConsole(sc, bookService, rentalService);

        while (true) {
            System.out.println("==============================");
            System.out.println("      Library Management System");
            System.out.println("==============================");
            System.out.println("1. User Login");
            System.out.println("2. Sing Up");
            System.out.println("0. Exit");
            System.out.print("Select > ");
            String sel = sc.nextLine().trim();
            switch (sel) {
                case "1" -> {
                    Member login = loginConsole.doLoginWithRetry(3);
                    if (login != null) {
                        if ("ADMIN".equalsIgnoreCase(login.getRole())) {
                            adminConsole.loop(login);
                        } else {
                            userConsole.loop(login);
                        }
                    }
                }
                case "2" -> {
                    signUpConsole.doSignUp();
                }
                case "0" -> {
                    System.out.println("Bye!");
                    return;
                }
                default -> System.out.println("Invalid input. Please try again.");
            }
            System.out.println();
        }
    }
}