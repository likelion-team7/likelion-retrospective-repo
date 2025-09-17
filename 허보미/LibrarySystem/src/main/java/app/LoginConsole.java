package app;

import domain.Member;
import service.MemberService;
import java.io.Console;
import java.util.Scanner;

public class LoginConsole {
    private final Scanner sc;
    private final MemberService memberService;

    public LoginConsole(Scanner sc, MemberService memberService) {
        this.sc = sc; this.memberService = memberService;
    }

    public Member doLoginWithRetry(int max) {
        for (int i = 0; i < max; i++) {
            String u = prompt("Username: ");
            String p = prompt("Password: ");
            Member m = memberService.login(u, p);

            if (m != null) {
                System.out.println("Login success! Hello, " + m.getUsername() + " (" + m.getRole() + ")");
                return m;
            }

            System.out.println("Invalid credentials. Try again.");
        }

        return null;
    }

    private String prompt(String label) {
        System.out.print(label); return sc.nextLine().trim();
    }

}