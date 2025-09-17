package app;

import domain.Member;
import service.BookService;
import service.RentalService;

import java.util.Scanner;

public class UserConsole {
    private final Scanner sc;
    private final BookService bookService;
    private final RentalService rentalService;

    public UserConsole(Scanner sc, BookService bookService, RentalService rentalService) {
        this.sc = sc;
        this.bookService = bookService;
        this.rentalService = rentalService;
    }

    public void loop(Member user) {
        while (true) {
            System.out.println("=== User Menu ===");
            System.out.println("1. Rent Book");
            System.out.println("2. Return Book");
            System.out.println("3. List Books");
            System.out.println("0. Back");
            System.out.print("> ");

            switch (sc.nextLine().trim()) {
                case "1" -> rent(user);
                case "2" -> returnBook(user);
                case "3" -> listBooks();
                case "0" -> { return; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void rent(Member user) {
        bookService.findAvailable().forEach(System.out::println);
        System.out.print("Book ID to rent: ");
        long bookId = Long.parseLong(sc.nextLine().trim());
        try {
            rentalService.rent(bookId, user.getId(), 7);
            System.out.println("Rented! due in 7 days.");
        } catch (Exception e) {
            System.out.println("Fail to rent: " + e.getMessage());
        }
    }

    private void returnBook(Member user) {
        System.out.print("Book ID to return: ");
        long bookId = Long.parseLong(sc.nextLine().trim());
        try {
            rentalService.returnBook(bookId, user.getId());
            System.out.println("Returned. Thanks!");
        } catch (Exception e) {
            System.out.println("Fail to return: " + e.getMessage());
        }
    }

    private void listBooks() {
        bookService.findAll().forEach(System.out::println);
    }
}
