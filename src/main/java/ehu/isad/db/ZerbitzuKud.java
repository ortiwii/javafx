package ehu.isad.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ZerbitzuKud {

    private static final ZerbitzuKud instance = new ZerbitzuKud();

    public static ZerbitzuKud getInstance() {
        return instance;
    }

    private ZerbitzuKud() {
    }
    public void KenduZerbitzua (String zerbitzua){
        String query = "delete from services where izena = '"+zerbitzua+"';";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        dbKudeatzaile.execSQL(query);
    }
    public List<String> lortuLiburuak() {

        String query = "select isbn, argitaletxea, orriKop, irudiak, izena from liburua";
        DBKudeatzaile dbKudeatzaile = DBKudeatzaile.getInstantzia();
        ResultSet rs = dbKudeatzaile.execSQL(query);

        List<String> emaitza = new ArrayList<>();
        try {
            while (rs.next()) {

                String isbn = rs.getString("isbn");
                String arg = rs.getString("argitaletxea");
                int orriKop = rs.getInt("orriKop");
                String irudia = rs.getString("irudiak");
                String izena = rs.getString("izena");

                System.out.println(isbn+ ":" +izena+":"+arg+":"+orriKop+":"+irudia);
                emaitza.add(izena);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        }

        return emaitza;
    }
}

