package day10;

public class Book {
    private long id;
    private String author;
    private String title;
    private boolean isRented;

    public Book(long id, String author, String title) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.isRented = false;//대여가능
    }

    //getter,setter
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public boolean isRented() {
        return isRented;
    }
    public void setRented(boolean rented) {
        isRented = rented;
    }


    //toString재정의.
    @Override
    public String toString() {
        String status= isRented ? "대출중" : "대여가능";
        return "책번호:"+id+"번 저자:"+author+"의 "+title+"은 "+status;
    }






}
