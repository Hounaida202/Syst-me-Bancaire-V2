
package DAO;

import Entity.Client;
import Utilitaire.Database;
import Utilitaire.Validation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;


public class ClientDAO {

    public static void addClient(Client client) {
        String sql = "INSERT INTO Clients (id_client, nom, email) VALUES (?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, client.id_client());
            stmt.setString(2, client.nom());
            stmt.setString(3, client.email());
            stmt.executeUpdate();
            System.out.println("Client " + client.id_client()+ " ajouté avec succés");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void DeleteClient(Client client) {
        String sql = "DELETE FROM clients WHERE id_client = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // ❌ Tu appelles Validation.SiClientExiste(client.id_client())
            // mais ta méthode SiClientExiste attend un String, pas un UUID
            // ⚠️ Problème de type → erreur de compilation
            // ✅ Solution : convertir UUID → String avec toString()
            if (Validation.SiClientExiste(client.id_client().toString())) {

                stmt.setString(1, client.id_client()); // ici on peut garder UUID directement
                stmt.executeUpdate();
                System.out.println(" Client supprimé avec succès");

            } else {
                System.out.println(" Client non trouvé.");
            }

        } catch (SQLException e) {
            e.printStackTrace(); // ❌ pas idéal, mais au moins tu vois l’erreur
            // ✅ Solution conseillée : throw new RuntimeException(e); pour remonter l’erreur
        }
    }



}
