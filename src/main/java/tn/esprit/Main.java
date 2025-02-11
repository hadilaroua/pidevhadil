package tn.esprit;

import services.CategorieService;
import services.ProjetService;
import models.Categorie;
import models.Projet;

import java.sql.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            // Create service instances
            CategorieService categorieService = new CategorieService();
            ProjetService projetService = new ProjetService();

            // Add a new category
            Categorie newCategorie = new Categorie(0, "Software Development");
            categorieService.addCategorie(newCategorie);

            // Fetch and print all categories
            List<Categorie> categories = categorieService.getAllCategories();
            for (Categorie categorie : categories) {
                System.out.println("Category ID: " + categorie.getIdCateg() + ", Name: " + categorie.getNom());
            }

            // Add a new project with the fetched category (assuming the category with ID 1 exists)
            Categorie existingCategorie = categorieService.getCategorieById(1); // Example category ID 1
            if (existingCategorie != null) {
                Projet newProjet = new Projet("E-commerce Platform", "Online shopping system", "Java, Spring Boot", existingCategorie);
                projetService.addProjet(newProjet);
            }

            // Fetch and print all projects
            ResultSet projets = projetService.getAllProjets();
            while (projets.next()) {
                System.out.println("Project ID: " + projets.getInt("idProjet") + ", Name: " + projets.getString("nom") +
                        ", Description: " + projets.getString("description") + ", Technologies: " + projets.getString("technologie"));
            }

            // Update a project (example: Update project with ID 1)
            projetService.updateProjet(1, "Updated E-commerce Platform", "Updated description", "Java, React", existingCategorie);

            // Delete a project (example: Delete project with ID 1)
            projetService.deleteProjet(1);

            // Update a category
            categorieService.updateCategorie(1, "Updated Software Development");

            // Delete a category
            categorieService.deleteCategorie(2);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
