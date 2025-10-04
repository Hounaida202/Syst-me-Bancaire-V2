

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

    public static void addEpargneAccount(CompteEpargne compte) {

        String sql = "INSERT INTO comptes VALUES (?, ?, ?, ?, ? ,?)";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setObject(1, compte.getId_compte());
            stmt.setString(2, compte.getNumero());
            stmt.setDouble(3, compte.getSolde());
            stmt.setObject(4, compte.getId_client());
            stmt.setObject(5,null);
            stmt.setDouble(6, compte.getTaux());
            stmt.executeUpdate();
            System.out.println("CompteEpargne " + compte.getId_client()+ " ajouté avec succés");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public static Compte getminimumSolde(){
        String sql ="SELECT * FROM comptes ORDER BY solde ASC LIMIT 1";
        try {
            Connection conn = Database.getConnection();

            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                String id = rs.getString("id_compte");
                String numero = rs.getString("numero");
                double solde = rs.getDouble("solde");
                String id_client = rs.getString("id_client");
                Double decouvert = rs.getDouble("decouvert");
                Double taux = rs.getDouble("taux");
                if(decouvert == null){
                    Compte compte = new CompteEpargne(id,solde,id_client,taux);
                }
                else {
                    Compte compte = new CompteCourant(id,solde,id_client,decouvert);
                }
            }
            else {
                return null;
            }

        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return null;
    }



}
