package ehu.isad.controller;

import com.google.gson.Gson;
import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.util.StringConverter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;


public class LiburuakKuk implements Initializable {

    Liburuak main;
    Sarea sarea = new Sarea();

    @FXML
    private ComboBox cbox;

    @FXML
    void OnClick(ActionEvent event) {

        Book book = (Book)cbox.getValue();
        try {
            book = sarea.readFromUrl(book.isbn);
            main.xehetasunakErakutsi(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ObservableList<Book> books = FXCollections.observableArrayList();
        books.addAll(
                new Book("1491910399", "R for Data Science"),
                new Book("1491946008", "Fluent Python"),
                new Book("9781491906187", "Data Algorithms")
        );
        cbox.setItems(books);
        cbox.setEditable(false);
        StringConverter<Book> sc = new StringConverter<Book>() {
            @Override
            public String toString(Book book) {
                if (book==null) {
                    return "";
                }else{
                    return book.title;
                }
            }

            @Override
            public Book fromString(String string) {
                return null;
            }
        };
        cbox.setConverter(sc);

    }
    public void setMain(Liburuak liburuak){
        this.main = liburuak;
    }
}
