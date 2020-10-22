package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.db.ZerbitzuKud;
import ehu.isad.utils.Sarea;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
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
            if (!ZerbitzuKud.getInstance().iburuaDago(book)){ //Beraz liburua bilatu behar da OpenLibraryn
                Book lib = sarea.readFromUrl(book.ISBN);
                ZerbitzuKud.getInstance().gehituLiburua(lib);
            }
            main.xehetasunakErakutsi(book.ISBN);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        List<Book> list = ZerbitzuKud.getInstance().lortuLiburuak();
        ObservableList<Book> books = FXCollections.observableArrayList(list);
        cbox.setItems(books);
    }
    public void setMain(Liburuak liburuak){
        this.main = liburuak;
    }
}
