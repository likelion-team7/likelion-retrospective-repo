package domain;

import java.time.LocalDate;

public class Rental {
    private long id;
    private long memberId;
    private long bookId;
    private LocalDate rentedAt;
    private LocalDate dueDate;
    private LocalDate returnedAt;

    public Rental() {}
    public Rental(long id, long memberId, long bookId, LocalDate rentedAt, LocalDate dueDate, LocalDate returnedAt) {
        this.id = id; this.memberId = memberId; this.bookId = bookId;
        this.rentedAt = rentedAt; this.dueDate = dueDate; this.returnedAt = returnedAt;
    }

    public long getId() { return id; }
    public long getMemberId() { return memberId; }
    public long getBookId() { return bookId; }
    public LocalDate getRentedAt() { return rentedAt; }
    public LocalDate getDueDate() { return dueDate; }
    public LocalDate getReturnedAt() { return returnedAt; }

    public void setId(long id) { this.id = id; }
    public void setMemberId(long memberId) { this.memberId = memberId; }
    public void setBookId(long bookId) { this.bookId = bookId; }
    public void setRentedAt(LocalDate rentedAt) { this.rentedAt = rentedAt; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }
    public void setReturnedAt(LocalDate returnedAt) { this.returnedAt = returnedAt; }

    @Override public String toString() {
        return "Rental{id=" + id + ", memberId=" + memberId + ", bookId=" + bookId +
               ", rentedAt=" + rentedAt + ", dueDate=" + dueDate + ", returnedAt=" + returnedAt + "}";
    }
}
