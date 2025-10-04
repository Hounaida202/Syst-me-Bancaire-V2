
package Entity;


import java.util.UUID;

public sealed class Compte permits CompteCourant,CompteEpargne {
            private String id_compte;
            private String numero;
            private Double solde;
            private String id_client;
            public static int compteur = 1;
            public Compte(String id_compte,Double solde , String id_client) {
                this.id_compte=id_compte;
                this.numero = "CPT-" + String.format("%05d", compteur++);
                this.solde=solde;
                this.id_client=id_client;
    }
    public String getId_compte() {
                return id_compte;
    }
    public String getNumero() {
                return numero;
    }
    public void setNumero(String numero) {
                this.numero = numero;
    }
    public double getSolde() {
                return solde;
    }
    public void setSolde(Double solde) {
                this.solde = solde;
    }
    public String getId_client() {
                return id_client;
    }
        }
