package com.pharmacie.model;

import org.json.JSONObject;

public class Patient {
    private String nom;
    private String prenom;
    private String adresse;
    private int numeroSecuriteSociale;

    public Patient(String nom, String prenom, String adresse, int numeroSecuriteSociale) {
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.numeroSecuriteSociale = numeroSecuriteSociale;
    }

    public String getNom() { return nom; }
    public String getPrenom() { return prenom; }
    public String getAdresse() { return adresse; }
    public int getNumeroSecuriteSociale() { return numeroSecuriteSociale; }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nom", nom);
        jsonObject.put("prenom", prenom);
        jsonObject.put("adresse", adresse);
        jsonObject.put("nss", numeroSecuriteSociale);
        return jsonObject;
    }

    public static Patient fromJSONObject(JSONObject jsonObject) {
        return new Patient(
                jsonObject.getString("nom"),
                jsonObject.getString("prenom"),
                jsonObject.getString("adresse"),
                jsonObject.getInt("nss")
        );
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Adresse: " + adresse + ", NSS: " + numeroSecuriteSociale;
    }
}