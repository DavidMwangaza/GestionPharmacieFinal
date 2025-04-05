package org.example;

import java.util.ArrayList;
import java.util.List;

public class Fournisseur {
    private String nom;
    private String adresse;
    private String numeroTelephone;
    private List<Medicament> medicamentsFournis; // Relation avec Medicament

    public Fournisseur(String nom, String adresse, String numeroTelephone) {
        this.nom = nom;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.medicamentsFournis = new ArrayList<>();
    }

    public String getNom() { return nom; }
    public String getAdresse() { return adresse; }
    public String getNumeroTelephone() { return numeroTelephone; }
    public List<Medicament> getMedicamentsFournis() { return new ArrayList<>(medicamentsFournis); }

    public void ajouterMedicamentFourni(Medicament medicament) {
        this.medicamentsFournis.add(medicament);
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Adresse: " + adresse + ", Téléphone: " + numeroTelephone;
    }
}
