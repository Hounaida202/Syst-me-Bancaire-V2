
package Presentation;

import java.util.Scanner;

import Metier.TransactionService;
import Utilitaire.*;

import Metier.ClientService;
import Metier.CompteService;
public class Menu {

    private Scanner scanner;

    public Menu() {
        scanner = new Scanner(System.in);
    }
    public void afficherMenu() {
        int choix;
        do {
            System.out.println("1- Ajouter un client");
            System.out.println("2 - modifier les données d'un client");
            System.out.println("3 - supprimer un client");
            System.out.println("4 - rechercher un client");
            System.out.println("5 - lister les clients");
            System.out.println("6 - creer un compte");
            System.out.println("7 - faire un retrait depuis un compte");
//            System.out.println("8 - statistiques des comptes");
            System.out.println("8 - virer un montant");
            System.out.println("9 - faire un versement entre deux comptes");

            System.out.println("10- quitter");

            choix = Validation.verifierchoix(scanner, 1, 20);
            System.out.println("\n");
            ClientService cs=new ClientService();
            CompteService cp=new CompteService();
            TransactionService Tr = new TransactionService();

            switch (choix) {
                case 1:
                    cs.addClientService();
                    break;
                case 2:
                    cs.updateClientService();
                    break;
                case 3:
                    cs.deleteClientService();
                    break;
                case 4:
                    System.out.println("1- pour choisir par nom");
                    System.out.println("2- pour choisir par id");
                    int choix2 = Validation.verifierchoix(scanner, 1, 2);
                    switch (choix2) {
                        case 1:
                            cs.searchClientService1();
                            break;
                            case 2:
                                cs.searchClientService2();
                                break;
                    }

                    break;

                case 5:
                    cs.ClientsList();
                    break;
                case 6:
                    System.out.println("1- pour creer un compte courant ");
                    System.out.println("2- pour creer un compte epargne");
                    int choix3 = Validation.verifierchoix(scanner, 1, 2);
                    switch (choix3) {
                        case 1:
                            cp.addCompteService1();
                            break;
                        case 2:
                            cp.addCompteService2();
                            break;
                    }

                    break;
//                case 6:
//                    System.out.println("1- le compte avec le minimum de solde");
//                    System.out.println("2- le compte avec le maximum de solde");
//                    int choix4 = Validation.verifierchoix(scanner, 1, 2);
//                    switch (choix4) {
//                        case 1:
//                            cp.minimumSolde();
//                            break;
//                        case 2:
//                            cp.maximumSolde();
//                            break;
//                    }
//
//                    break;
                case 7:
                    Tr.Faireretrait();
                case 8:
                    Tr.Fairevirement();
                case 9:
                    Tr.Faireversement();


                case 20:
                    System.out.println("programme exité");
                    break;
            }
        } while (choix != 20);
    }

}
