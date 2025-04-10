package org.example;

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

    @Override
    public String toString() {
        return "Nom: " + nom + ", Prénom: " + prenom + ", Adresse: " + adresse + ", NSS: " + numeroSecuriteSociale;
    }
}