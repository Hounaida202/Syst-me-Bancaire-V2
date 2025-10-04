
package DAO;

import Entity.Compte;
import Entity.CompteCourant;
import Entity.CompteEpargne;
import Entity.Transaction;
import Utilitaire.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class TransactionDAO {

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

            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Virement effectué avec succès !");
            } else {
                System.out.println("Aucun compte mis à jour.");
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transactions VALUES (?, ? ,? ,? ,?,?)";
        try {
            Connection conn = Database.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1,transaction.id_transaction());
            stmt.setDate(2, java.sql.Date.valueOf(transaction.date()));
            stmt.setDouble(3,transaction.montant());
            stmt.setString(4,transaction.type().name());
            stmt.setString(5,transaction.lieu());
            stmt.setString(6,transaction.id_compte());

            stmt.executeUpdate();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


}
