import Utilitaire.Database;
import Presentation.Menu;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        if(Database.getConnection()==null){System.out.println("fen a jmi");}
        else{System.out.println("fen");}
        System.out.println("menu");
        Menu menu = new Menu();
        menu.afficherMenu();

    }
}

