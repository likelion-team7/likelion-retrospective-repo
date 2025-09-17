package repository;

import domain.Rental;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RentalDAO {
    //대출
    long insert(long bookId, long memberId, LocalDate rentedAt, LocalDate dueDate);
    //반납
    boolean returnBook(long rentalId, LocalDate returnedAt);
    Optional<Rental> findActiveByBook(long bookId);
    List<Rental> findByMember(long memberId);
}
