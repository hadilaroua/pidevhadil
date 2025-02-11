package tn.esprit;

import services.CategorieService;
import services.ProjetService;
import models.Categorie;
import models.Projet;

import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            // Create service instances
            CategorieService categorieService = new CategorieService();
            ProjetService projetService = new ProjetService();

            // Fetch a category by ID (example: ID = 1)
            int categorieId = 1;
            Categorie categorie = categorieService.getCategorieById(categorieId);

            if (categorie != null) {
                // Add a new project with the fetched category
                Projet projet = new Projet("E-commerce App", "Online shopping platform", "Java, Spring Boot", categorie);
                projetService.addProjet(projet);

                // Retrieve and print all projects
                ResultSet projets = projetService.getAllProjets();
                while (projets.next()) {
                    System.out.println("Project ID: " + projets.getInt("idProjet") + ", Name: " + projets.getString("nom") +
                            ", Description: " + projets.getString("description") + ", Technologies: " + projets.getString("technologie"));
                }



                // Update a project
                projetService.updateProjet(1, "Updated E-commerce App", "Updated Description", "Java, Angular", categorie);

                // Delete a project
                projetService.deleteProjet(1);
            } else {
                System.out.println("Category not found!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
