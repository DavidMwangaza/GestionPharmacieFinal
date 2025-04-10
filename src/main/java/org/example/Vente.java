package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Vente {
    private int idVente;
    private Date dateVente;
    private double montantTotal;
    private List<Medicament> medicaments;
    private Patient patient;

    public Vente(int idVente, Date dateVente, List<Medicament> medicaments, Patient patient) {
        this.idVente = idVente;
        this.dateVente = dateVente;
        this.medicaments = new ArrayList<>(medicaments); // Créer une copie pour éviter les modifications externes
        this.patient = patient;
        this.montantTotal = calculerMontantTotal();
    }

    public int getIdVente() { return idVente; }
    public Date getDateVente() { return dateVente; }
    public double getMontantTotal() { return montantTotal; }
    public List<Medicament> getMedicaments() { return new ArrayList<>(medicaments); } // Retourner une copie
    public Patient getPatient() { return patient; }

    private double calculerMontantTotal() {
        double total = 0;
        for (Medicament medicament : medicaments) {
            total += medicament.getPrix();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("ID Vente: ").append(idVente)
                .append(", Date: ").append(dateVente)
                .append(", Montant Total: ").append(montantTotal)
                .append(", Patient: ").append(patient != null ? patient.getNom() + " " + patient.getPrenom() : "N/A")
                .append(", Médicaments:\n");
        for (Medicament medicament : medicaments) {
            sb.append("- ").append(medicament.getNom()).append("\n");
        }
        return sb.toString();
    }
}