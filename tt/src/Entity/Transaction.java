package Entity;

import java.time.LocalDate;
import java.util.UUID;
import java.time.LocalDate;

public record Transaction(String id_transaction, LocalDate date, double montant,Type type, String lieu,String id_compte) {
    public enum Type {VERSEMENT,RETRAIT,VIREMENT}
}

