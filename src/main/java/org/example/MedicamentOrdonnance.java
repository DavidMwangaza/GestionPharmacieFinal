package com.pharmacie.model;

import org.json.JSONObject;

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
    public JSONObject toJSONObject() {
        JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("type", "ORD");
        jsonObject.put("dosage", dosage);
        jsonObject.put("contreIndications", contreIndications);
        return jsonObject;
    }

    public static MedicamentOrdonnance fromJSONObject(JSONObject jsonObject) {
        return new MedicamentOrdonnance(
                jsonObject.getString("nom"),
                jsonObject.getString("code"),
                jsonObject.getDouble("prix"),
                jsonObject.getInt("stock"),
                jsonObject.getString("dosage"),
                jsonObject.getString("contreIndications")
        );
    }

    @Override
    public String toString() {
        return super.toString() + ", Dosage: " + dosage + ", Contre-indications: " + contreIndications;
    }
}