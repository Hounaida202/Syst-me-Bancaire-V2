
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


}
