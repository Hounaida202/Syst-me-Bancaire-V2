package Entity;

import java.util.UUID;

public final class CompteCourant extends Compte {

    private double decouvert;

    public CompteCourant(String id_compte , Double solde , String id_client , double decouvert) {
    super(id_compte  , solde , id_client );
    this.decouvert = decouvert;
    }

    public double getDecouvert() {
        return decouvert;
    }

    public void setDecouvert(double decouvert) {
        this.decouvert = decouvert;
    }
}
