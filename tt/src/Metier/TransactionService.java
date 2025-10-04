package Metier;

import DAO.CompteDAO;
import DAO.TransactionDAO;
import Entity.Transaction;

import java.time.LocalDate;
import java.util.Scanner;
import java.util.UUID;

public class TransactionService {
    public static void Faireretrait() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Entrer l'id du compte à retirer :");
        String id = sc.nextLine();

        System.out.println("Entrer le montant à retirer :");
        Double montant = sc.nextDouble();
        sc.nextLine();
        System.out.println("Veuillez choisir votre pays :");
        String lieu = sc.nextLine();
        Transaction.Type type = Transaction.Type.RETRAIT;
        CompteDAO.retrait(id, montant);
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                montant,
                type,
                lieu,
                id
        );
        TransactionDAO.addTransaction(transaction);
    }

    public static void Fairevirement(){
        System.out.println("entrer l id ce compte a virer:");
        Scanner sc = new Scanner(System.in);
        String id= sc.nextLine();
        System.out.println("entrer le montant a virer:");
        Double montant = sc.nextDouble();
        sc.nextLine();
        System.out.println("Veuillez choisir votre pays :");
        String lieu = sc.nextLine();
        Transaction.Type type = Transaction.Type.VIREMENT;
        CompteDAO.virement(id, montant);
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                montant,
                type,
                lieu,
                id
        );
        TransactionDAO.addTransaction(transaction);
    }

    public static void Faireversement(){
        System.out.println("entrer le code de compte destination : ");
        Scanner sc = new Scanner(System.in);
        String destination= sc.nextLine();
        System.out.println("entrer le code de compte source : ");
        String source= sc.nextLine();
        System.out.println("entrer le montant : ");
        Double montant= sc.nextDouble();
        sc.nextLine();
        System.out.println("Veuillez choisir votre pays :");
        String lieu = sc.nextLine();
        Transaction.Type type = Transaction.Type.VERSEMENT;
        CompteDAO.retrait(source,montant);
        CompteDAO.virement(destination,montant);
        Transaction transaction = new Transaction(
                UUID.randomUUID().toString(),
                LocalDate.now(),
                montant,
                type,
                lieu,
                source
        );
        TransactionDAO.addTransaction(transaction);
    }
}
