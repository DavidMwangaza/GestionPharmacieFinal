package com.pharmacie.model;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;

public class Fournisseur {
    private String nom;
    private String adresse;
    private String numeroTelephone;
    private List<Medicament> medicamentsFournis; // Relation avec Medicament (non persistée ici pour simplifier)

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

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nom", nom);
        jsonObject.put("adresse", adresse);
        jsonObject.put("telephone", numeroTelephone);
        return jsonObject;
    }

    public static Fournisseur fromJSONObject(JSONObject jsonObject) {
        return new Fournisseur(
                jsonObject.getString("nom"),
                jsonObject.getString("adresse"),
                jsonObject.getString("telephone")
        );
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Adresse: " + adresse + ", Téléphone: " + numeroTelephone;
    }
}