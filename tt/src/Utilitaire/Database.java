package Utilitaire;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    static {
        // Force le chargement du driver au démarrage
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("Driver PostgreSQL chargé avec succès !");
        } catch (ClassNotFoundException e) {
            throw new ExceptionInInitializerError("Driver PostgreSQL non trouvé: " + e.getMessage());
        }
    }

    public static Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://localhost:5432/system-bancaire-v2";
        String user = "postgres"; // remplacez par votre username
        String password = "etrichi"; // remplacez par votre mot de passe

        return DriverManager.getConnection(url, user, password);
    }
}