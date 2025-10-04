package Entity;

import java.util.UUID;

public final class CompteEpargne extends Compte {

    private double taux;

    public CompteEpargne(String id_compte , Double solde , String id_client , double taux) {
        super(id_compte , solde , id_client );
        this.taux = taux;
    }

    public double getTaux() {
        return taux;
    }
    public void setTaux(double taux) {
        this.taux = taux;
    }

}