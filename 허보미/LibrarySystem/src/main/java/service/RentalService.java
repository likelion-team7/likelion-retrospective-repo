package service;

public interface RentalService {
    void rent(long bookId, long memberId, int days);
    void returnBook(long bookId, long memberId);
}
