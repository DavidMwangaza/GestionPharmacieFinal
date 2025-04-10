package org.example;

public class MedicamentVenteLibre extends Medicament {
    private String description;

    public MedicamentVenteLibre(String nom, String code, double prix, int stock, String description) {
        super(nom, code, prix, stock);
        this.description = description;
    }

    public String getDescription() { return description; }

    @Override
    public String toString() {
        return super.toString() + ", Description: " + description;
    }
}