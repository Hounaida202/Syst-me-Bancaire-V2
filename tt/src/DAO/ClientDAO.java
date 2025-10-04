

package DAO;

import Entity.Client;
import Entity.CompteCourant;
import Entity.CompteEpargne;
import Utilitaire.Database;
import Utilitaire.Validation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import Entity.Compte;

public class CompteDAO {
    public static void addCourantAccount(CompteCourant compte) {

        String sql = "INSERT INTO comptes VALUES (?, ?, ?, ?, ? ,?)";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1, compte.getId_compte());
            stmt.setString(2, compte.getNumero());
            stmt.setDouble(3, compte.getSolde());
            stmt.setObject(4, compte.getId_client());
            stmt.setDouble(5, compte.getDecouvert());
            stmt.setObject(6, null);
            stmt.executeUpdate();
            System.out.println("CompteCourant " + compte.getId_client()+ " ajouté avec succés");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



}
