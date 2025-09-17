package repository;

import domain.Book;
import java.util.List;
import java.util.Optional;

public interface BookDAO {
    //책추가
    long save(Book b);                     // INSERT, 반환: 생성된 id
    //책 삭제
    boolean delete(long id);
    //id조회
    //Optional클래스 : Null 값 방지
    Optional<Book> findById(long id);
    //전체조회
    List<Book> findAll();
    //대출가능 책 조회
    List<Book> findAvailable();
    // 렌트 상태 업데이트 rented = false
    boolean updateRented(long id, boolean rented);
}
