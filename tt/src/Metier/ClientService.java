

package Metier;

import DAO.ClientDAO;
import Entity.Client;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class ClientService {
    public static void addClientService() {
        System.out.println("~~Adding client :) service~~");
        System.out.println("Le nom du client svp :) ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        System.out.println("L'email du client svp :) ");
        String email = sc.nextLine();

        Client client = new Client( UUID.randomUUID().toString(),nom, email);
        ClientDAO.addClient(client);

    }
//    public static void deleteClientService() {
//        System.out.println("~~Delete client :) service~~");
//        System.out.println("L'id du client à supprimer :( ");
//        Scanner sc = new Scanner(System.in);
//        String id = sc.nextLine();
//        ClientDAO.DeleteClient(id);
//
//    }

//    public static void updateClientService() {
//        System.out.println("~~Update client :) service~~");
//        System.out.println("L'id du client svp :) ");
//        Scanner sc = new Scanner(System.in);
    ////        UUID id = sc.next();
//        String idStr = sc.next();
//        UUID id = UUID.fromString(idStr);
//        ClientDAO.getClientByID(id);
//        System.out.println("Le nom du client svp :) ");
//        String nom = sc.nextLine();
//        System.out.println("Le email du client svp :) ");
//        String email = sc.nextLine();
//        Client client = new Client( id, nom, email);
//        ClientDAO.EditClient(client);
//
//
//    }
    public static void updateClientService() {
        System.out.println("~~Update client :) service~~");
        System.out.println("L'id du client svp :) ");
        Scanner sc = new Scanner(System.in);

        // ❌ Tu avais mis : String idStr = sc.next();
        // ⚠️ Problème : next() lit seulement jusqu’au premier espace et parfois cause des soucis
        // ✅ Correction : utiliser nextLine() pour récupérer la ligne complète
        String id = sc.nextLine();

        // ❌ Tu avais : UUID id = sc.next();
        // ⚠️ Problème : sc.next() retourne une String, pas un UUID → erreur de compilation
        // ✅ Correction : convertir en UUID avec fromString()

        // ❌ Tu avais bien appelé getClientByID mais tu ignorais le retour
        // ⚠️ Problème : si le client n’existe pas, tu continues quand même
        // ✅ Correction : vérifier si le client existe
        Client existingClient = ClientDAO.getClientByID(id);
        if (existingClient == null) {
            System.out.println(" Aucun client trouvé avec cet ID !");
            return; // on arrête la méthode ici
        }

        System.out.println("Le nom du client svp :) ");
        // ❌ Tu avais mis nextLine() juste après next() → ça lisait une ligne vide
        // ⚠️ Problème : Scanner garde un retour à la ligne en mémoire
        // ✅ Correction : utiliser directement nextLine() ici (après avoir nettoyé avant)
        String nom = sc.nextLine();

        System.out.println("Le email du client svp :) ");
        String email = sc.nextLine();

        // ❌ Tu créais bien le client, mais sans vérifier qu'il correspond à l’ID
        // ✅ Correction : on garde le même id qu’au départ, et on met à jour les champs
        Client client = new Client(id, nom, email);

        // Appel de la DAO
        ClientDAO.EditClient(client);

        System.out.println(" Client mis à jour avec succès !");
    }

    public static void deleteClientService() {
        System.out.println("~~Delete client :) service~~");
        System.out.println("L'id du client svp :) ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();
        Client client = ClientDAO.getClientByID(id);
        ClientDAO.DeleteClient(client);

    }
    public static void searchClientService1() {
        System.out.println("~~svp tapez le nom du client :) ");
        Scanner sc = new Scanner(System.in);
        String nom = sc.nextLine();
        Client client = ClientDAO.getClientByNom(nom);
        System.out.println("L'id de client est : "+ client.id_client());
        System.out.println("L'email du client est : "+ client.email());
    }
    public static void searchClientService2() {
        System.out.println("~~svp tapez le ID du client :) ");
        Scanner sc = new Scanner(System.in);
        String id = sc.nextLine();

        Client client = ClientDAO.getClientByID(id);
        System.out.println("Le Nom de client est : "+ client.nom());
        System.out.println("L'email du client est : "+ client.email());
    }

    public static void ClientsList() {
        List<Client> clients = ClientDAO.getAllclients();
        for (Client client : clients) {
            System.out.println("le ID du client : " + client.id_client());
            System.out.println("le Nom du client : " + client.nom());
            System.out.println("le email du client : " + client.email()+"\n");
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        }
    }


}
