package ehu.isad;

public class Book {
    public String ISBN;
    public String title;

    public String info_url;
    public String bib_key;
    public String preview_url;
    public String thumbnail_url;
    public Details details;

    public Book(String isbn, String title) {
        this.ISBN = isbn;
        this.title = title;
    }
    @Override
    public String toString() {
        return title;
    }
}

