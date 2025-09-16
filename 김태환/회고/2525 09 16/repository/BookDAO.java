package net.likelion.repository;

import net.likelion.dto.BookDTO;

import java.util.List;

public interface BookDAO {
    public boolean insert(BookDTO book);
    public boolean update(BookDTO book);
    public boolean delete(BookDTO book);
    public List<BookDTO> findAll();
    public List<BookDTO> findByTitle(String title);
}
