package ehu.isad.utils;

import com.google.gson.Gson;
import ehu.isad.Book;
import javafx.scene.image.Image;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class Sarea {
    public Book readFromUrl(String isbn) {
        Book book = null;
        URL openl = null;
        try {
            openl = new URL("https://openlibrary.org/api/books?bibkeys=ISBN:"+isbn+"&jscmd=details&format=json");
            URLConnection yc = openl.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
            String inputLine = in.readLine();
            in.close();
            String[] zatiak = inputLine.split("ISBN:"+isbn+"\":");
            inputLine = zatiak[1].substring(0, zatiak[1].length()-1);
            Gson gson = new Gson();
            book = gson.fromJson(inputLine, Book.class);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
        return book;
    }
    public Image createImage(String url) throws IOException {
        URLConnection conn = new URL(url).openConnection();
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_6) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/85.0.4183.121 Safari/537.36");
        try (InputStream stream = conn.getInputStream()) {
            return new Image(stream);
        }
    }

}
