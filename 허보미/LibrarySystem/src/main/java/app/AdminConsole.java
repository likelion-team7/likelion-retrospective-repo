package app;

import domain.Member;
import service.BookService;
import service.MemberService;
import java.util.Scanner;

public class AdminConsole {
    private final Scanner sc;
    private final BookService bookService;
    private final MemberService memberService;
    public AdminConsole(Scanner sc, BookService bookService, MemberService memberService) {
        this.sc = sc; this.bookService = bookService; this.memberService = memberService;
    }
    public void loop(Member admin) {
        while (true) {
            System.out.println("=== Admin Menu ===");
            System.out.println("1. Add Book");
            System.out.println("2. Delete Book");
            System.out.println("3. List Books");
            System.out.println("4. List Members");
            System.out.println("0. Back");
            System.out.print("> ");

            switch (sc.nextLine().trim()) {
                case "1" -> addBook();
                case "2" -> deleteBook();
                case "3" -> listBooks();
                case "4" -> listMembers();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void addBook() {
        System.out.print("Title: ");
        String t = sc.nextLine().trim();
        System.out.print("Author: ");
        String a = sc.nextLine().trim();
        long id = bookService.addBook(t, a);
        System.out.println("Added. id=" + id);
    }

    private void deleteBook() {
        System.out.print("Book ID: ");
        long id = Long.parseLong(sc.nextLine().trim());
        boolean ok = bookService.delete(id);
        System.out.println(ok ? "Deleted." : "Not found.");
    }

    private void listBooks() {
        bookService.findAll().forEach(System.out::println);
    }

    private void listMembers() {
        memberService.findAll().forEach(System.out::println);
    }
}