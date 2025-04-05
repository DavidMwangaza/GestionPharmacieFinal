package org.example;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @Override
    public String toString() {
        return "Nom: " + nom + ", Code: " + code + ", Prix: " + prix + ", Stock: " + stock;
    }
}

