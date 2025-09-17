package service;

import domain.Book;
import java.util.List;

public interface BookService {
    long addBook(String title, String author);
    boolean delete(long id);
    List<Book> findAll();
    List<Book> findAvailable();
}
