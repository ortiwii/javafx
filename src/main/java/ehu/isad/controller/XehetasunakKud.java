package ehu.isad.controller;

import ehu.isad.Book;
import ehu.isad.Liburuak;
import ehu.isad.utils.Sarea;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

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
    public void setInfo (Book book) throws IOException {
        Image pic = sarea.createImage(book.thumbnail_url);
        this.irudia.setImage(pic);
        String arg = book.details.publishers[0];
        for (int i = 1; i < book.details.publishers.length; i ++){
            arg = arg +", "+ book.details.publishers[i];
        }
        this.argitaletxea.setText(arg);
        this.izenburua.setText(book.title);
        this.orriKop.setText(book.details.number_of_pages.toString());
    }
    public void setMain(Liburuak liburuak){
        this.main = liburuak;
    }

}