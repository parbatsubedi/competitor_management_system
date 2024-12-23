package com.competitorManagement.repository;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.entity.Name;

import java.sql.*;
import java.util.*;

public class CompetitorRepository {
    private final String url = "jdbc:mysql://localhost:3306/CompetitionDB";
    private final String user = "root";
    private final String password = "";

    public void save(Competitor competitor) throws SQLException {
        String query = "INSERT INTO Competitors VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, competitor.getCompetitorID());
            stmt.setString(2, competitor.getName().getFirstName());
            stmt.setString(3, competitor.getName().getLastName());
            stmt.setString(4, competitor.getLevel());
            stmt.setString(5, competitor.getCountry());

            int[] scores = competitor.getScores();
            for (int i = 0; i < scores.length; i++) {
                stmt.setInt(6 + i, scores[i]);
            }

            stmt.executeUpdate();
        }
    }

    public Optional<Competitor> findById(int id) {
        String query = "SELECT * FROM Competitors WHERE CompetitorID = ?";
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return Optional.of(createCompetitorFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    public List<Competitor> findAll() {
        List<Competitor> competitors = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Competitors")) {

            while (rs.next()) {
                competitors.add(createCompetitorFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return competitors;
    }

    private Competitor createCompetitorFromResultSet(ResultSet rs) throws SQLException {
        int[] scores = {
                rs.getInt("Score1"), rs.getInt("Score2"),
                rs.getInt("Score3"), rs.getInt("Score4"), rs.getInt("Score5")
        };

        return new Competitor(
                rs.getInt("CompetitorID"),
                new Name(rs.getString("FirstName"), rs.getString("LastName")),
                rs.getString("Level"),
                rs.getString("Country"),
                scores
        );
    }
}
