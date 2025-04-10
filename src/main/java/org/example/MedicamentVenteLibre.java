package com.pharmacie.model;

import org.json.JSONObject;

public class MedicamentVenteLibre extends Medicament {
    private String description;

    public MedicamentVenteLibre(String nom, String code, double prix, int stock, String description) {
        super(nom, code, prix, stock);
        this.description = description;
    }

    public String getDescription() { return description; }

    @Override
    public JSONObject toJSONObject() {
        JSONObject jsonObject = super.toJSONObject();
        jsonObject.put("type", "VL");
        jsonObject.put("description", description);
        return jsonObject;
    }

    public static MedicamentVenteLibre fromJSONObject(JSONObject jsonObject) {
        return new MedicamentVenteLibre(
                jsonObject.getString("nom"),
                jsonObject.getString("code"),
                jsonObject.getDouble("prix"),
                jsonObject.getInt("stock"),
                jsonObject.getString("description")
        );
    }

    @Override
    public String toString() {
        return super.toString() + ", Description: " + description;
    }
}