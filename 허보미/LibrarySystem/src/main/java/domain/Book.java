package domain;

public class Book {
    private long id;
    private String title;
    private String author;
    private boolean rented;

    //생성자
    public Book() {}

    public Book(long id, String title, String author, boolean rented) {
        this.id = id; this.title = title; this.author = author; this.rented = rented;
    }

    //getter/setter
    public long getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public boolean isRented() { return rented; }

    public void setId(long id) { this.id = id; }
    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setRented(boolean rented) { this.rented = rented; }

    @Override public String toString() {
        return "Book{id=" + id + ", title='" + title + "', author='" + author + "', rented=" + rented + "}";
    }
}
