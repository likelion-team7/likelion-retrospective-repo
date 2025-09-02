package day10;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Library {
    private List<Book> bookList;
    private long nextBookId;
    public Library() {
        this.nextBookId = 1;
        this.bookList = new ArrayList<>();
    }
    public void addBook(String author, String title){
        Book book=new Book(nextBookId,author,title);
        bookList.add(book);
        nextBookId++;
        System.out.println("도서 추가");
    }
    public void listAllBooks(){
        for(Book book:bookList){//전체순회
            System.out.println(book.toString());
            }
        }

    public void searchBook(String title){
        boolean found=false;//
        if(bookList.isEmpty()){
            System.out.println("현재 도서가 없습니다.");
            return;
        }
        for(Book book:bookList){
            if(book.getTitle().equals(title)){//제목과비교
                System.out.println(book);
                found=true;
            }
        }
        if(!found){
            System.out.print("검색결과가 없습니다.");
        }
    }

    public void rentBook(String title){//도서대여 조건
        boolean found=false;
        for(Book book:bookList){
            if(book.getTitle().equals(title)){
                if(book.isRented()){
                    found= true;
                    System.out.println("이미 대여중인 도서입니다.");
                }
                else {
                    book.setRented(true);
                    System.out.println(book.getTitle()+"대여 완료");
                    found=true;
                }
                return;
            }
        }

    }

    public void returnBook(String title){
        for(Book book:bookList){
            if(book.getTitle().equals(title)){
                if(!book.isRented()){
                    System.out.println("현재 도서관에 반납되어있습니다.");
                }
                else{
                    book.setRented(false);
                    System.out.println(book.getTitle()+"반납 완료");
                }
                return;
            }
        }
    }
//    public void removeBook(long bookId) {
//        for (Book book : bookList) {
//            if (book.getId()) if (!bookList.isEmpty()) {
//                if (book.isRented()) {
//                    System.out.println("이미 대여중인 도서로 삭제가 안됩니다.");
//                } else {
//                    bookList.remove(book);
//                }
//                System.out.println("성공적으로 제거되었습니다.");
//                System.out.println(book.toString());
//                System.out.println();
//            } else {
//                System.out.println("현재 도서가 없습니다.");
//                return;
//            }
//
//        }
//    }
}






