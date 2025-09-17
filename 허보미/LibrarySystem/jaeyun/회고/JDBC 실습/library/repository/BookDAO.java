package library.repository;

import library.dto.BookDTO;

import java.util.List;

public interface BookDAO {
    public boolean addBook(BookDTO book);
    public boolean updateBook(BookDTO book);
    public boolean deleteBook(BookDTO book);
    public List<BookDTO> getBooks();
    public BookDTO getBook(String title);


}
