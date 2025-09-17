package service;

import domain.Book;
import repository.BookDAO;

import java.util.List;

public class BookServiceImpl implements BookService {
    private final BookDAO bookDAO;
    public BookServiceImpl(BookDAO bookDAO) { this.bookDAO = bookDAO; }

    @Override public long addBook(String title, String author) {
        Book b = new Book(0, title, author, false);
        return bookDAO.save(b);
    }
    @Override public boolean delete(long id) { return bookDAO.delete(id); }
    @Override public List<Book> findAll() { return bookDAO.findAll(); }
    @Override public List<Book> findAvailable() { return bookDAO.findAvailable(); }
}
