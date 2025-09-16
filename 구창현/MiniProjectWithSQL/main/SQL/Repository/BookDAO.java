package Repository;

import Sql.DTO.BookDTO;

import java.util.List;

public interface BookDAO {
    //crud 메서드
    public void addBook(BookDTO bookDTO);
    public void updateBook(BookDTO bookDTO);
    public void deleteBook(BookDTO bookDTO);
    public List<BookDTO> getAllBooks();
    public List<BookDTO> getBooksByName(String name);
    //책하나만 찾는거를 id통해서 조회해야할거같은데 나중에 추가
    //중복된 책은? <- 같이 출력


}
