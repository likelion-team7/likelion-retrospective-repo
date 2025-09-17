package app;

import domain.Member;
import service.MemberService;

import java.time.LocalDateTime;
import java.util.Scanner;

public class SignUpConsole {
    private final Scanner sc;
    private final MemberService memberService;

    public SignUpConsole(Scanner sc, MemberService memberService) {
        this.sc = sc; this.memberService = memberService;
    }

    public Member doSignUp() {
        String u;
        String p;
        int r = 0;

        while (true) {
            System.out.println("<SIGN UP>");
            u = prompt("Username: ");
            p = prompt("Password: ");
            r = promptRole("Role (1.User, 2.Admin): ");
            String role = (r == 1) ? "USER" : "ADMIN";

            boolean isExistUser = memberService.findUserName(u);

            if (isExistUser) {
                System.out.println("Already exists username " + u);
                System.out.println("Please try again");
                // 다시 입력 루프 시작
                continue;
            } else {


                // DB에 insert
                int result = memberService.register(u, p, role);

                if (result > 0) {
                    Member m = new Member(0, u, p, role, LocalDateTime.now());
                    System.out.println("Sign Up success! Hello, "
                            + m.getUsername() + " (" + m.getRole() + ")");
                    return m;
                } else {
                    System.out.println("Sign Up failed. Try again.");
                }
            }
        }

    }

    private String prompt(String label) {
        System.out.print(label); return sc.nextLine().trim();
    }

    private int promptRole(String label) {
        while (true) {
            String input = prompt(label); // 문자열 입력
            try {
                int r = Integer.parseInt(input);
                if (r == 1 || r == 2) {
                    return r; // 올바른 값이면 그대로 반환
                } else {
                    System.out.println("Please enter 1 or 2");
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter 1 or 2");
            }
        }
    }


}
