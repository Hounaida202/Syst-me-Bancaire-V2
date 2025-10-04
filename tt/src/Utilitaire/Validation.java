package Utilitaire;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;
import java.util.UUID;

public class Validation {
    public static int verifierchoix(Scanner scanner, int min, int max) {
        int choix;
        while (true) {
            System.out.print("Choix : ");
            if (scanner.hasNextInt()) {
                choix = scanner.nextInt();
                scanner.nextLine();
                if (choix >= min && choix <= max) {
                    return choix;
                }
            } else {
                scanner.nextLine();
            }
            System.out.println("choix invalide");
        }
    }

//    public static boolean SiClientExiste(String id_client) {
//        String sql = "SELECT count(*) as total FROM clients WHERE id_client = ?";
//
//        try (Connection conn = Database.getConnection();
//             PreparedStatement stmt = conn.prepareStatement(sql)) {
//
//            stmt.setObject(1, UUID.fromString(id_client));
//            ResultSet resultat = stmt.executeQuery();
//
//            if (resultat.next()) {
//                int count = resultat.getInt("total");
//                if (count >= 1) {
//                    return
//                            true;
//                } else
//                    return false;
//            }
//
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }
public static boolean SiClientExiste(String id_client) {
    String sql = "SELECT count(*) as total FROM clients WHERE id_client = ?";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        // ❌ Tu avais UUID.fromString(id_client) ici
        // ⚠️ C’est correct si tu passes toujours un String valide UUID
        // ✅ Donc c’est bon, mais ça impose que le String soit bien un UUID
        stmt.setObject(1, UUID.fromString(id_client));

        ResultSet resultat = stmt.executeQuery();

        if (resultat.next()) {
            int count = resultat.getInt("total");

            // ❌ if/else trop verbeux
            // ✅ Simplification : return directement une condition
            return count >= 1;
        }

    } catch (Exception e) {
        e.printStackTrace();
        // ❌ ici tu avales l’erreur → dangereux
        // ✅ bonne pratique : remonter l’erreur pour ne pas ignorer un bug
        // throw new RuntimeException(e);
    }
    return false; // ✅ valeur par défaut si aucun résultat
}

}
