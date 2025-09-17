package service;

import repository.BookDAO;
import repository.RentalDAO;

import java.time.LocalDate;

public class RentalServiceImpl implements RentalService {
    private final BookDAO bookDAO;
    private final RentalDAO rentalDAO;

    public RentalServiceImpl(BookDAO bookDAO, RentalDAO rentalDAO) {
        this.bookDAO = bookDAO; this.rentalDAO = rentalDAO;
    }

    @Override
    public void rent(long bookId, long memberId, int days) {
        var book = bookDAO.findById(bookId).orElseThrow(() -> new IllegalArgumentException("Book not found"));
        if (book.isRented()) throw new IllegalStateException("Already rented");
        var rentedAt = LocalDate.now();
        var dueDate = rentedAt.plusDays(days);
        rentalDAO.insert(bookId, memberId, rentedAt, dueDate);
        bookDAO.updateRented(bookId, true);
    }

    @Override
    public void returnBook(long bookId, long memberId) {
        var active = rentalDAO.findActiveByBook(bookId)
                .orElseThrow(() -> new IllegalStateException("No active rental"));
        if (active.getMemberId() != memberId) { /* 정책적으로 막고 싶으면 예외 */
            // throw new IllegalStateException("This rental belongs to another member");
        }
        rentalDAO.returnBook(active.getId(), LocalDate.now());
        bookDAO.updateRented(bookId, false);
    }
}
