# Application d’Analyse des Transactions Bancaires

## Contexte

La Banque Al Baraka souhaite une application pour mieux gérer ses transactions et détecter les anomalies.  
Ce projet, réalisé par SoluBank Systems, a pour but de :

- Centraliser les données clients, comptes et transactions.  
- Détecter les opérations suspectes.  
- Identifier les comptes inactifs.  
- Générer des rapports pour aider à la prise de décision.  

---

## Structure du projet

### Interface (UI)
Menu textuel permettant de :
- Créer un client et ses comptes.
- Enregistrer une transaction (versement, retrait, virement).
- Consulter l’historique des transactions.
- Lancer une analyse (top 5 clients, comptes inactifs, etc.).

### Couche Métier (Services)
Contient la logique principale :
- **ClientService** : gérer les clients.
- **CompteService** : gérer les comptes.
- **TransactionService** : gérer et filtrer les transactions, détecter les anomalies.
- **RapportService** : générer des rapports et statistiques.

### Couche Entity
Objets du domaine :
- **Client** → id, nom, email.
- **Compte** → id, numéro, solde, client.
- **CompteCourant** → découvert autorisé.
- **CompteEpargne** → taux d’intérêt.
- **Transaction** → id, date, montant, type, lieu, compte.

### Couche DAO
Communication avec la base de données (MySQL ou PostgreSQL) :
- **ClientDAO**, **CompteDAO**, **TransactionDAO** pour les opérations CRUD.

### Couche Utilitaire
- Formatage des montants et dates.
- Validation des saisies utilisateur.

---

## Technologies
- **Langage :** Java 17  
- **Base de données :** MySQL / PostgreSQL  
- **Connexion :** JDBC  
- **Architecture :** en couches  

---

## Fonctionnalités principales
- Gestion des clients et comptes  
- Suivi des transactions  
- Détection des anomalies  
- Rapports et statistiques  
- Identification des comptes inactifs  
