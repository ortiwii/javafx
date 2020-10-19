package ehu.isad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import ehu.isad.controller.LiburuakKuk;
import ehu.isad.controller.XehetasunakKud;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Liburuak extends Application {

    private Stage stage;
    private Scene sceneLiburuak;
    private Scene sceneXehetasunak;
    private LiburuakKuk liburuakKud;
    private XehetasunakKud xehetasunakKud;

    private Book readFromUrl(String isbn) throws IOException, MalformedURLException {
        URL openlibrary = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
        URLConnection yc = openlibrary.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        String inputLine = in.readLine();
        in.close();

        String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
        inputLine = zatiak[1].substring(0, zatiak[1].length()-1);

        Gson gson = new Gson();
        return gson.fromJson(inputLine, Book.class);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        this.pantailakKargatu();

        stage.setTitle("Liburuak");
        stage.setScene(sceneLiburuak);
        stage.show();
    }
    private void pantailakKargatu() throws IOException {
        FXMLLoader loaderLiburuak = new FXMLLoader(getClass().getResource("/Liburuak.fxml"));
        Parent liburuakUI = (Parent) loaderLiburuak.load();
        liburuakKud = loaderLiburuak.getController();
        liburuakKud.setMain(this);
        sceneLiburuak = new Scene(liburuakUI);

        FXMLLoader loaderXehetasunak = new FXMLLoader(getClass().getResource("/xehetasunak.fxml"));
        Parent xehetasunakUI = (Parent) loaderXehetasunak.load();
        xehetasunakKud = loaderXehetasunak.getController();
        xehetasunakKud.setMain(this);
        sceneXehetasunak = new Scene(xehetasunakUI);
    }
    public void xehetasunakErakutsi(Book book) throws IOException {
        this.xehetasunakKud.setInfo(book);
        stage.setScene(sceneXehetasunak);
        stage.show();
    }
    public void setHasierakoMenua ()  throws IOException {
        stage.setScene(sceneLiburuak);
        stage.show();
    }
}
