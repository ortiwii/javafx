package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.db.ZerbitzuKud;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;
import java.util.List;

public class XehetasunakKud {

    Liburuak main;
    Sarea sarea = new Sarea();

    @FXML
    private Label izenburua;

    @FXML
    private Label argitaletxea;

    @FXML
    private Label orriKop;

    @FXML
    private ImageView irudia;

    @FXML
    void onClick(ActionEvent event) {
        try {
            this.main.setHasierakoMenua();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void setInfo (String isbn) throws IOException {
        Image pic = ZerbitzuKud.getInstance().irekiIrudia(isbn);
        this.irudia.setImage(pic);
        List<String> lista = ZerbitzuKud.getInstance().getLiburua(isbn);
        this.argitaletxea.setText(lista.get(0));
        this.orriKop.setText(lista.get(1));
        this.izenburua.setText(lista.get(2));

    }
    public void setMain(Liburuak liburuak){
        this.main = liburuak;
    }

}