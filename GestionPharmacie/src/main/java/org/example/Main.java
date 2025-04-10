package com.pharmacie;

import com.pharmacie.model.*;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<Medicament> medicaments = new ArrayList<>();
    private static List<Patient> patients = new ArrayList<>();
    private static List<Fournisseur> fournisseurs = new ArrayList<>();
    private static List<Vente> ventes = new ArrayList<>();
    private static int nextVenteId = 1;

    private static final String DATA_FILE = "pharmacie_data.json";
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Bienvenue dans l'application de gestion de pharmacie !");

        loadData(); // Charger les données au démarrage

        int choix;
        do {
            afficherMenu();
            System.out.print("Entrez votre choix : ");
            choix = scanner.nextInt();
            scanner.nextLine(); // Consommer la nouvelle ligne

            switch (choix) {
                case 1: ajouterMedicament(); break;
                case 2: afficherMedicaments(); break;
                case 3: ajouterPatient(); break;
                case 4: afficherPatients(); break;
                case 5: ajouterFournisseur(); break;
                case 6: afficherFournisseurs(); break;
                case 7: enregistrerVente(); break;
                case 8: afficherVentes(); break;
                case 0:
                    saveData(); // Sauvegarder les données avant de quitter
                    System.out.println("Merci d'avoir utilisé l'application !");
                    break;
                default: System.out.println("Choix invalide. Veuillez réessayer.");
            }
            System.out.println();
        } while (choix != 0);

        scanner.close();
    }

    private static void afficherMenu() {
        System.out.println("--- Menu ---");
        System.out.println("1. Ajouter un médicament");
        System.out.println("2. Afficher les médicaments");
        System.out.println("3. Ajouter un patient");
        System.out.println("4. Afficher les patients");
        System.out.println("5. Ajouter un fournisseur");
        System.out.println("6. Afficher les fournisseurs");
        System.out.println("7. Enregistrer une vente");
        System.out.println("8. Afficher les ventes");
        System.out.println("0. Quitter");
    }

    private static void ajouterMedicament() {
        System.out.print("Nom du médicament : ");
        String nom = scanner.nextLine();
        System.out.print("Code du médicament : ");
        String code = scanner.nextLine();
        System.out.print("Prix du médicament : ");
        double prix = scanner.nextDouble();
        System.out.print("Stock du médicament : ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        System.out.println("Type de médicament (VL/ORD) : ");
        String type = scanner.nextLine().toUpperCase();

        Medicament medicament;
        if (type.equals("VL")) {
            System.out.print("Description : ");
            String description = scanner.nextLine();
            medicament = new MedicamentVenteLibre(nom, code, prix, stock, description);
        } else if (type.equals("ORD")) {
            System.out.print("Dosage : ");
            String dosage = scanner.nextLine();
            System.out.print("Contre-indications : ");
            String contreIndications = scanner.nextLine();
            medicament = new MedicamentOrdonnance(nom, code, prix, stock, dosage, contreIndications);
        } else {
            System.out.println("Type de médicament invalide. Création d'un médicament de base.");
            medicament = new Medicament(nom, code, prix, stock);
        }
        medicaments.add(medicament);
        System.out.println("Médicament ajouté avec succès !");
    }

    private static void afficherMedicaments() {
        if (medicaments.isEmpty()) {
            System.out.println("Aucun médicament enregistré.");
            return;
        }
        System.out.println("--- Liste des médicaments ---");
        for (Medicament medicament : medicaments) {
            System.out.println(medicament);
        }
    }

    private static void ajouterPatient() {
        System.out.print("Nom du patient : ");
        String nom = scanner.nextLine();
        System.out.print("Prénom du patient : ");
        String prenom = scanner.nextLine();
        System.out.print("Adresse du patient : ");
        String adresse = scanner.nextLine();
        System.out.print("Numéro de sécurité sociale du patient : ");
        int nss = scanner.nextInt();
        scanner.nextLine(); // Consommer la nouvelle ligne

        Patient patient = new Patient(nom, prenom, adresse, nss);
        patients.add(patient);
        System.out.println("Patient ajouté avec succès !");
    }

    private static void afficherPatients() {if (patients.isEmpty()) {
        System.out.println("Aucun patient enregistré.");
        return;
    }
        System.out.println("--- Liste des patients ---");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }

    private static void ajouterFournisseur() {
        System.out.print("Nom du fournisseur : ");
        String nom = scanner.nextLine();
        System.out.print("Adresse du fournisseur : ");
        String adresse = scanner.nextLine();
        System.out.print("Numéro de téléphone du fournisseur : ");
        String telephone = scanner.nextLine();

        Fournisseur fournisseur = new Fournisseur(nom, adresse, telephone);
        fournisseurs.add(fournisseur);
        System.out.println("Fournisseur ajouté avec succès !");
    }

    private static void afficherFournisseurs() {
        if (fournisseurs.isEmpty()) {
            System.out.println("Aucun fournisseur enregistré.");
            return;
        }
        System.out.println("--- Liste des fournisseurs ---");
        for (Fournisseur fournisseur : fournisseurs) {
            System.out.println(fournisseur);
        }
    }

    private static void enregistrerVente() {
        if (medicaments.isEmpty() || patients.isEmpty()) {
            System.out.println("Veuillez d'abord ajouter des médicaments et des patients.");
            return;
        }

        System.out.println("--- Enregistrer une vente ---");

        // Sélection du patient
        System.out.println("Sélectionnez un patient par son NSS :");
        afficherPatients();
        System.out.print("Entrez le NSS du patient : ");
        int nssPatient = scanner.nextInt();
        scanner.nextLine();
        Patient patientSelectionne = null;
        for (Patient patient : patients) {
            if (patient.getNumeroSecuriteSociale() == nssPatient) {
                patientSelectionne = patient;
                break;
            }
        }
        if (patientSelectionne == null) {
            System.out.println("Patient non trouvé.");
            return;
        }

        // Sélection des médicaments
        List<Medicament> medicamentsVendus = new ArrayList<>();
        boolean continuer = true;
        while (continuer) {
            System.out.println("Sélectionnez un médicament à ajouter à la vente par son code (ou 'terminer') :");
            afficherMedicaments();
            System.out.print("Entrez le code du médicament : ");
            String codeMedicament = scanner.nextLine();
            if (codeMedicament.equalsIgnoreCase("terminer")) {
                continuer = false;
            } else {
                Medicament medicamentSelectionne = null;
                for (Medicament medicament : medicaments) {
                    if (medicament.getCode().equalsIgnoreCase(codeMedicament)) {
                        medicamentSelectionne = medicament;
                        break;
                    }
                }
                if (medicamentSelectionne != null && medicamentSelectionne.getStock() > 0) {
                    medicamentsVendus.add(medicamentSelectionne);
                    medicamentSelectionne.setStock(medicamentSelectionne.getStock() - 1); // Simuler la vente
                    System.out.println(medicamentSelectionne.getNom() + " ajouté à la vente.");
                } else if (medicamentSelectionne != null && medicamentSelectionne.getStock() <= 0) {
                    System.out.println("Stock insuffisant pour " + medicamentSelectionne.getNom());
                } else {
                    System.out.println("Médicament non trouvé.");
                }
            }
        }

        if (!medicamentsVendus.isEmpty()) {
            Vente vente = new Vente(nextVenteId++, new Date(), medicamentsVendus, patientSelectionne);
            ventes.add(vente);
            System.out.println("Vente enregistrée avec succès !");
            System.out.println(vente);
        } else {
            System.out.println("Aucun médicament sélectionné pour la vente.");
        }
    }

    private static void afficherVentes() {
        if (ventes.isEmpty()) {
            System.out.println("Aucune vente enregistrée.");
            return;
        }
        System.out.println("--- Liste des ventes ---");
        for (Vente vente : ventes) {
            System.out.println(vente);
        }
    }

    private static void loadData() {
        try {
            String content = new String(Files.readAllBytes(Paths.get(DATA_FILE)));
            JSONObject root = new JSONObject(content);

            // Charger les médicaments
            JSONArray medicamentsArray = root.getJSONArray("medicaments");
            for (int i = 0; i < medicamentsArray.length(); i++) {
                JSONObject medicamentObj = medicamentsArray.getJSONObject(i);
                String type = medicamentObj.getString("type");
                if (type.equals("VL")) {
                    medicaments.add(MedicamentVenteLibre.fromJSONObject(medicamentObj));
                } else if (type.equals("ORD")) {
                    medicaments.add(MedicamentOrdonnance.fromJSONObject(medicamentObj));
                } else {
                    medicaments.add(Medicament.fromJSONObject(medicamentObj));
                }
            }

            // Charger les patients
            JSONArray patientsArray = root.getJSONArray("patients");
            for (int i = 0; i < patientsArray.length(); i++) {
                JSONObject patientObj = patientsArray.getJSONObject(i);
                patients.add(Patient.fromJSONObject(patientObj));
            }

            // Charger les fournisseurs
            JSONArray fournisseursArray = root.getJSONArray("fournisseurs");
            for (int i = 0; i < fournisseursArray.length(); i++) {
                JSONObject fournisseurObj = fournisseursArray.getJSONObject(i);
                fournisseurs.add(Fournisseur.fromJSONObject(fournisseurObj));
            }

            // Charger les ventes
            JSONArray ventesArray = root.getJSONArray("ventes");
            for (int i = 0; i < ventesArray.length(); i++) {
                JSONObject venteObj = ventesArray.getJSONObject(i);
                ventes.add(Vente.fromJSONObject(venteObj, medicaments, patients));
                if (venteObj.getInt("idVente") >= nextVenteId) {
                    nextVenteId = venteObj.getInt("idVente") + 1;
                }
            }

        } catch (IOException e) {
            System.out.println("Erreur lors du chargement des données : " + e.getMessage());
            // Si le fichier n'existe pas, on commence avec des listes vides
        } catch (org.json.JSONException e) {
            System.out.println("Erreur lors de la lecture du fichier JSON : " + e.getMessage());
        }
    }

    private static void saveData() {
        JSONObject root = new JSONObject();
        JSONArray medicamentsArray = new JSONArray();
        for (Medicament medicament : medicaments) {
            medicamentsArray.put(medicament.toJSONObject());
        }
        root.put("medicaments", medicamentsArray);

        JSONArray patientsArray = new JSONArray();
        for (Patient patient : patients) {
            patientsArray.put(patient.toJSONObject());
        }
        root.put("patients", patientsArray);

        JSONArray fournisseursArray = new JSONArray();
        for (Fournisseur fournisseur : fournisseurs) {
            fournisseursArray.put(fournisseur.toJSONObject());
        }
        root.put("fournisseurs", fournisseursArray);

        JSONArray ventesArray = new JSONArray();
        for (Vente vente : ventes) {
            ventesArray.put(vente.toJSONObject());
        }
        root.put("ventes", ventesArray);

        try (FileWriter file = new FileWriter(DATA_FILE)) {
            file.write(root.toString(4)); // Indentation pour une meilleure lisibilité
            System.out.println("Données sauvegardées avec succès dans " + DATA_FILE);
        } catch (IOException e) {
            System.out.println("Erreur lors de la sauvegarde des données : " + e.getMessage());
        }
    }
}