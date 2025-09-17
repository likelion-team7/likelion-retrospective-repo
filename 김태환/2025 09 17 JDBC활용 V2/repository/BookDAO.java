package net.likelion.repository;

import net.likelion.domain.dto.BookDTO;

import java.util.List;

public interface BookDAO {
    boolean insert(BookDTO book);
    boolean update(BookDTO book);
    boolean delete(BookDTO book);
    List<BookDTO> findAll();
    List<BookDTO> findByTitle(String title);
}
