package com.pharmacie.model;

import org.json.JSONObject;

public class Medicament {
    private String nom;
    private String code;
    private double prix;
    private int stock;

    public Medicament(String nom, String code, double prix, int stock) {
        this.nom = nom;
        this.code = code;
        this.prix = prix;
        this.stock = stock;
    }

    public String getNom() { return nom; }
    public String getCode() { return code; }
    public double getPrix() { return prix; }
    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

    public JSONObject toJSONObject() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("nom", nom);
        jsonObject.put("code", code);
        jsonObject.put("prix", prix);
        jsonObject.put("stock", stock);
        jsonObject.put("type", "BASE");
        return jsonObject;
    }

    public static Medicament fromJSONObject(JSONObject jsonObject) {
        return new Medicament(
                jsonObject.getString("nom"),
                jsonObject.getString("code"),
                jsonObject.getDouble("prix"),
                jsonObject.getInt("stock")
        );
    }

    @Override
    public String toString() {
        return "Nom: " + nom + ", Code: " + code + ", Prix: " + prix + ", Stock: " + stock;
    }
}