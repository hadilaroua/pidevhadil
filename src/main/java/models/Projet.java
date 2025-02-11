package models;

public class Projet {
    private int idProjet;
    private String nom;
    private String description;
    private String technologie;  // Changed to technologie
    private int categorieId;

    // Constructor with Categorie object
    public Projet(String nom, String description, String technologie, Categorie categorie) {
        this.nom = nom;
        this.description = description;
        this.technologie = technologie;  // Changed to technologie
        this.categorieId = categorie.getIdCateg();  // Set the category ID
    }

    // Getter and setter for technologie
    public String getTechnologie() {
        return technologie;
    }

    public void setTechnologie(String technologie) {
        this.technologie = technologie;  // Changed to technologie
    }

    // Getter and setter for categorieId
    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    // Getters and setters for other attributes
    public int getIdProjet() {
        return idProjet;
    }

    public void setIdProjet(int idProjet) {
        this.idProjet = idProjet;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
