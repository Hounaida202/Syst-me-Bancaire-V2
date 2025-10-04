

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

    public static Compte getCompteById(String id_compte) {
        String sql = "SELECT * FROM comptes WHERE id_compte = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, id_compte);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String id = rs.getString("id_compte");
                    double solde = rs.getDouble("solde");
                    String id_client = rs.getString("id_client");

                    // ⚠️ attention : rs.getDouble retourne 0.0 si NULL, donc il faut tester avec wasNull()
                    double decouvert = rs.getDouble("decouvert");
                    boolean hasDecouvert = !rs.wasNull();

                    double taux = rs.getDouble("taux");
                    boolean hasTaux = !rs.wasNull();

                    // Si c’est un CompteCourant
                    if (hasDecouvert) {
                        return new CompteCourant(id, solde, id_client, decouvert);
                    }
                    // Si c’est un CompteEpargne
                    else if (hasTaux) {
                        return new CompteEpargne(id, solde, id_client, taux);
                    }

                } else {
                    return null; // aucun compte trouvé
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }


    public static void retrait(String id_compte, Double montant) {
        String sql = "UPDATE comptes SET solde = ? WHERE id_compte = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 1️⃣ Récupérer le compte
            Compte compte = CompteDAO.getCompteById(id_compte);
            if (compte == null) {
                System.out.println("❌ Compte introuvable !");
                return;
            }

            // 2️⃣ Vérification selon le type de compte
            if (compte instanceof CompteCourant cc) {
                // cas Compte Courant : on peut descendre jusqu’à solde + decouvert
                if (montant > cc.getSolde() + cc.getDecouvert()) {
                    System.out.println("❌ Solde insuffisant (dépasse le découvert autorisé) !");
                    return;
                }
            } else if (compte instanceof CompteEpargne ce) {
                // cas Compte Epargne : on ne peut pas dépasser le solde
                if (montant > ce.getSolde()) {
                    System.out.println("❌ Solde insuffisant (pas assez sur le compte épargne) !");
                    return;
                }
            }

            // 3️⃣ Calculer le nouveau solde
            Double nouveauSolde = compte.getSolde() - montant;

            // 4️⃣ Remplir la requête SQL
            stmt.setDouble(1, nouveauSolde);
            stmt.setString(2, id_compte);

            // 5️⃣ Exécuter la mise à jour
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Virement effectué avec succès !");
            } else {
                System.out.println("⚠️ Aucun compte mis à jour.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void virement(String id_compte, Double montant) {
        String sql = "UPDATE comptes SET solde = ? WHERE id_compte = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // 1️⃣ Récupérer le compte
            Compte compte = CompteDAO.getCompteById(id_compte);
            if (compte == null) {
                System.out.println("❌ Compte introuvable !");
                return;
            }

            // 2️⃣ Calculer le nouveau solde (ajout du montant)
            Double nouveauSolde = compte.getSolde() + montant;

            // 3️⃣ Remplir la requête SQL
            stmt.setDouble(1, nouveauSolde);
            stmt.setString(2, id_compte);

            // 4️⃣ Exécuter la mise à jour
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("✅ Virement effectué avec succès !");
            } else {
                System.out.println("⚠️ Aucun compte mis à jour.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
