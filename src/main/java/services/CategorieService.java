package services;

import models.Categorie;
import java.sql.*;

public class CategorieService {
    private Connection connection;

    public CategorieService() throws SQLException {
        connection = utils.DBConnection.getConnection();
    }

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
    }}

