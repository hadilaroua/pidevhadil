package models;

public class Categorie {
    private int idCateg;
    private String nom;

    public Categorie(int idCateg, String nom) {
        this.idCateg = idCateg;
        this.nom = nom;
    }

    public int getIdCateg() {
        return idCateg;
    }

    public void setIdCateg(int idCateg) {
        this.idCateg = idCateg;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
}
