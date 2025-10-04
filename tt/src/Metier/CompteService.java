
package Metier;

import DAO.CompteDAO;
import Entity.Compte;
import Entity.CompteCourant;
import Entity.CompteEpargne;

import java.util.Scanner;
import java.util.UUID;

public class CompteService {
    public static void addCompteService1() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");

        System.out.println("Veuillez entrer le ID du client :");
        String id= sc.nextLine();

        System.out.println("Veulliez entrer le solde");
        Double solde = sc.nextDouble();

        System.out.println("Veulliez entrer le decouvert");
        double decouvert = sc.nextDouble();
        CompteCourant compte = new CompteCourant(
                UUID.randomUUID().toString(),
                solde,
                id,
                decouvert
        );
        CompteDAO.addCourantAccount(compte);


    }
    public static void addCompteService2() {
        Scanner sc = new Scanner(System.in);
        System.out.println("");

        System.out.println("Veuillez entrer le ID du client :");
        String id= sc.nextLine();


        System.out.println("Veulliez entrer le solde");
        Double solde = sc.nextDouble();
        System.out.println("Veulliez entrer le taux en % :");
        double taux = sc.nextDouble();
        CompteEpargne compte = new CompteEpargne(
                UUID.randomUUID().toString(),
                solde,
                id,
                taux
        );
        CompteDAO.addEpargneAccount(compte);;

    }
    public static void minimumSolde() {
        System.out.println("le compte avec minimum de solde est:");

    }


}
