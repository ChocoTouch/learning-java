package fr.hetic;

public class Fichier {
    private final int id;
    private final String nom;
    private final String type;

    public Fichier(int id, String nom, String type) {
        this.id = id;
        this.nom = nom;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getType() {
        return type;
    }
}
