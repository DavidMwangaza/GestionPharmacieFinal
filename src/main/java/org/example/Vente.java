package com.pharmacie.model;

import org.json.JSONArray;
import org.json.JSONObject;
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
        this.medicaments = new ArrayList<>(medicaments);
        this.patient = patient;
        this.montantTotal = calculerMontantTotal();
    }

    public int getIdVente() { return idVente; }
    public Date getDateVente() { return dateVente; }
    public double getMontantTotal() { return montantTotal; }
    public List<Medicament> getMedicaments() { return new ArrayList<>(medicaments); }
    public Patient getPatient() { return patient; }

    private double calculerMontantTotal() {
        double total = 0;
        for (Medicament medicament : medicaments) {
            total += medicament.getPrix();
        }
        return total;
    }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("idVente", idVente);
        jsonObject.put("dateVente", dateVente.getTime());
        JSONArray medicamentsArray = new JSONArray();
        for (Medicament medicament : medicaments) {
            medicamentsArray.put(medicament.getCode());
        }
        jsonObject.put("medicaments", medicamentsArray);
        jsonObject.put("patientNss", patient.getNumeroSecuriteSociale());
        return jsonObject;
    }

    public static Vente fromJSONObject(JSONObject jsonObject, List<Medicament> allMedicaments, List<Patient> allPatients) {
        int idVente = jsonObject.getInt("idVente");
        Date dateVente = new Date(jsonObject.getLong("dateVente"));
        JSONArray medicamentsArray = jsonObject.getJSONArray("medicaments");
        List<Medicament> medicamentsVendus = new ArrayList<>();
        for (int i = 0; i < medicamentsArray.length(); i++) {
            String code = medicamentsArray.getString(i);
            for (Medicament m : allMedicaments) {
                if (m.getCode().equals(code)) {
                    medicamentsVendus.add(m);
                    break;
                }
            }
        }
        int patientNss = jsonObject.getInt("patientNss");
        Patient patientVente = null;
        for (Patient p : allPatients) {
            if (p.getNumeroSecuriteSociale() == patientNss) {
                patientVente = p;
                break;
            }
        }
        return new Vente(idVente, dateVente, medicamentsVendus, patientVente);
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