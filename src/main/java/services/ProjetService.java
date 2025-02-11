package services;

import models.Projet;
import models.Categorie;
import utils.DBConnection;  // Import DBConnection

import java.sql.*;

public class ProjetService {
    private Connection connection;

    public ProjetService() {
        // Initialize the database connection here (DBConnection should be your database connection class)
        try {
            this.connection = DBConnection.getConnection();  // Get connection from DBConnection class
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProjet(Projet projet) throws SQLException {
        String query = "INSERT INTO projet (nom, description, technologie, id_categ) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, projet.getNom());
            stmt.setString(2, projet.getDescription());
            stmt.setString(3, projet.getTechnologie());
            stmt.setInt(4, projet.getCategorieId());
            stmt.executeUpdate();
        }
    }


    public ResultSet getAllProjets() throws SQLException {
        String query = "SELECT * FROM projet";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }

    public void updateProjet(int id, String nom, String description, String technologie, Categorie categorie) throws SQLException {
        String query = "UPDATE projet SET nom = ?, description = ?, technologie = ?, id_categ = ? WHERE idProjet = ?";  // Changed 'id_projet' to 'idProjet'
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nom);
            stmt.setString(2, description);
            stmt.setString(3, technologie);
            stmt.setInt(4, categorie.getIdCateg());
            stmt.setInt(5, id);  // Make sure to set the correct project ID here
            stmt.executeUpdate();
        }
    }


    public void deleteProjet(int id) throws SQLException {
        String query = "DELETE FROM projet WHERE idProjet = ?";  // Changed 'id_projet' to 'idProjet'
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

}
