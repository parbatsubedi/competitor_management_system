package com.competitorManagement;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.entity.Name;

import java.sql.*;

public class Main {
    //use this schema to create database and tables
    /*
    CREATE DATABASE CompetitionDB;
    USE CompetitionDB;

    CREATE TABLE Competitors (
        CompetitorID INT PRIMARY KEY,
        FirstName VARCHAR(100),
        LastName VARCHAR(100),
        Level VARCHAR(50),
        Country VARCHAR(100),
        Score1 INT,
        Score2 INT,
        Score3 INT,
        Score4 INT,
        Score5 INT
    );
     */

    // Save competitor to database
    public static void saveToDatabase(Competitor competitor) {
        String url = "jdbc:mysql://localhost:3306/CompetitionDB";
        String user = "root";
        String password = "";

        String query = "INSERT INTO Competitors (CompetitorID, FirstName, LastName, Level, Country, Score1, Score2, Score3, Score4, Score5) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

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
            System.out.println("Competitor " + competitor.getCompetitorID() + " saved to database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Load competitors from database
    public static void loadFromDatabase() {
        String url = "jdbc:mysql://localhost:3306/CompetitionDB";
        String user = "root";
        String password = "";

        String query = "SELECT * FROM Competitors";

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                int id = rs.getInt("CompetitorID");
                String firstName = rs.getString("FirstName");
                String lastName = rs.getString("LastName");
                String level = rs.getString("Level");
                String country = rs.getString("Country");
                int[] scores = {
                        rs.getInt("Score1"), rs.getInt("Score2"),
                        rs.getInt("Score3"), rs.getInt("Score4"), rs.getInt("Score5")
                };

                Competitor competitor = new Competitor(id, new Name(firstName, lastName), level, country, scores);
                System.out.println(competitor.getFullDetails());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Input Data
        Competitor competitor1 = new Competitor(200, new Name("Alice", "Green"), "Beginner", "USA", new int[]{4, 3, 5, 2, 4});
        Competitor competitor2 = new Competitor(201, new Name("Bob", "Brown"), "Intermediate", "Canada", new int[]{3, 4, 4, 5, 4});
        Competitor competitor3 = new Competitor(202, new Name("Carol", "White"), "Advanced", "UK", new int[]{5, 5, 4, 4, 5});
        Competitor competitor4 = new Competitor(203, new Name("David", "Black"), "Advanced", "Australia", new int[]{4, 4, 5, 5, 4});

        // Save to database
        saveToDatabase(competitor1);
        saveToDatabase(competitor2);
        saveToDatabase(competitor3);
        saveToDatabase(competitor4);

        // Load from database
        System.out.println("\nCompetitors from Database:");
        loadFromDatabase();
    }
}
