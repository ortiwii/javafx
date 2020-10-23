package ehu.isad.db;

import ehu.isad.Book;
import ehu.isad.utils.Sarea;
import ehu.isad.utils.Utils;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZerbitzuKud {
    Sarea sarea = new Sarea();

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {    }

    public List<Book> lortuLiburuak() {

        String query = "select isbn,izena from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<Book> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {
                String isbn = rs.getString("isbn");
                String iz = rs.getString("izena");
                Book b = new Book(isbn, iz);
                emaitza.add(b);

            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }
    public boolean iburuaDago (Book book){
        String query = "SELECT * FROM LIBURUA WHERE isbn='"+book.ISBN+"' AND title IS NOT NULL;";
        ResultSet rs = DBKudeatzaile.getInstantzia().execSQL(query);
        boolean emaitza = false;
        try {
           emaitza = rs.next();
        }catch (SQLException e){
           e.printStackTrace();
       }
        return emaitza;
    }
    public void gehituLiburua (Book book) throws IOException {
        Image irudia = sarea.createImage(book.thumbnail_url);
        this.irudiaGehitu(irudia, book.ISBN);
        String arg = book.details.publishers[0];
        for (int i = 1; i < book.details.publishers.length; i ++){
            arg = arg+", "+book.details.publishers[i];
        }
        String query = "UPDATE liburua SET argitaletxea=\""+arg+"\", orriKop="+book.details.number_of_pages+", irudiak='"+book.thumbnail_url+"', title=\""+book.details.title+"\" WHERE isbn='"+book.ISBN+"';";
        DBKudeatzaile.getInstantzia().execSQL(query);
    }
    private void irudiaGehitu(Image image, String path) throws IOException {
        Properties properties = Utils.lortuEzarpenak();
        String format = "jpg" ;
        String pathToImages = properties.getProperty("pathToImages");
        String filepath = pathToImages+"/"+path;
        File file = new File(filepath) ;
        ImageIO.write(SwingFXUtils.fromFXImage(image, null), format, file);
    }
    public Image irekiIrudia (String path){
        Properties properties=Utils.lortuEzarpenak();
        String pathToImages = properties.getProperty("pathToImages");
        String filepath = pathToImages+"/"+path;
        File irudia = new File(filepath);
        BufferedImage img = null;
        try {
            img = ImageIO.read(irudia);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return SwingFXUtils.toFXImage(img, null);
    }
    public List<String> getLiburua (String isbn){
        String query = "SELECT argitaletxea, orriKop, title FROM LIBURUA WHERE isbn='"+isbn+"';";
        ResultSet rs = DBKudeatzaile.getInstantzia().execSQL(query);
        List<String> emaitza = new ArrayList<>();
        try{
            while(rs.next()){
                String arg = rs.getString("argitaletxea");
                Integer orriKop = rs.getInt("orriKop");
                String orriak = orriKop.toString();
                String izena = rs.getString("title");
                emaitza.add(arg);
                emaitza.add(orriak);
                emaitza.add(izena);
            }
        }catch (SQLException e){
            e.printStackTrace();;
        }
        return emaitza;
    }
}

