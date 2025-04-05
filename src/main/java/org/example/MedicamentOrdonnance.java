package org.example;

public class MedicamentOrdonnance extends Medicament {
    private String dosage;
    private String contreIndications;

    public MedicamentOrdonnance(String nom, String code, double prix, int stock, String dosage, String contreIndications) {
        super(nom, code, prix, stock);
        this.dosage = dosage;
        this.contreIndications = contreIndications;
    }

    public String getDosage() { return dosage; }
    public String getContreIndications() { return contreIndications; }

    @Override
    public String toString() {
        return super.toString() + ", Dosage: " + dosage + ", Contre-indications: " + contreIndications;
    }
}
