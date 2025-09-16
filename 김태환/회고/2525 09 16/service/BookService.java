package net.likelion.service;

import net.likelion.dto.BookDTO;
import net.likelion.repository.BookDAOImp;

import java.util.List;

public class BookService {
    private final BookDAOImp bookDAO;

    public BookService() {
        this.bookDAO = new BookDAOImp();
    }

    //도서추가
    public boolean addBook(BookDTO bookDTO){
        return bookDAO.insert(bookDTO);
    }

    //전체 도서 목록 조회
    public List<BookDTO> getAllBooks(){
        return bookDAO.findAll();
    }
    //제목으로 도서 검색
    public List<BookDTO> getBooksByTitle(String title){
        return bookDAO.findByTitle(title);
    }
    //도서 정보 업데이트
    public boolean updateBook(BookDTO bookDTO) {
        return bookDAO.update(bookDTO);
    }
    //도서 삭제
    public boolean deleteBook(BookDTO bookDTO) {
        return bookDAO.delete(bookDTO);
    }
}
