package services;

import models.Categorie;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategorieService {
    private Connection connection;

    public CategorieService() throws SQLException {
        connection = utils.DBConnection.getConnection();
    }

    // Create - Add a new category
    public void addCategorie(Categorie categorie) throws SQLException {
        String query = "INSERT INTO categorie (nom) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, categorie.getNom());
            int affectedRows = stmt.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        categorie.setIdCateg(generatedKeys.getInt(1));
                    }
                }
            }
        }
    }

    // Read - Get category by ID
    public Categorie getCategorieById(int id) throws SQLException {
        Categorie categorie = null;
        String query = "SELECT * FROM categorie WHERE id_categ = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                categorie = new Categorie(resultSet.getInt("id_categ"), resultSet.getString("nom"));
            }
        }
        return categorie;
    }

    // Read - Get all categories
    public List<Categorie> getAllCategories() throws SQLException {
        List<Categorie> categories = new ArrayList<>();
        String query = "SELECT * FROM categorie";
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                Categorie categorie = new Categorie(resultSet.getInt("id_categ"), resultSet.getString("nom"));
                categories.add(categorie);
            }
        }
        return categories;
    }

    // Update - Update category by ID
    public void updateCategorie(int id, String newNom) throws SQLException {
        String query = "UPDATE categorie SET nom = ? WHERE id_categ = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newNom);
            stmt.setInt(2, id);
            stmt.executeUpdate();
        }
    }

    // Delete - Delete category by ID
    public void deleteCategorie(int id) throws SQLException {
        String query = "DELETE FROM categorie WHERE id_categ = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
