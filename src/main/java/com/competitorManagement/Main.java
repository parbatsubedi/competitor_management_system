package com.competitorManagement;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.entity.Name;
import com.competitorManagement.repository.CompetitorRepository;
import com.competitorManagement.service.CompetitorService;

import java.sql.*;
import java.util.*;

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
        private static final Scanner scanner = new Scanner(System.in);
        private static final CompetitorService service = new CompetitorService(new CompetitorRepository());

        public static void main(String[] args) {
            while (true) {
                System.out.println("\n1. Add competitor");
                System.out.println("2. View competitor");
                System.out.println("3. Generate report");
                System.out.println("4. Exit");
                System.out.println("Enter Your Choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (choice) {
                        case 1 -> addCompetitor();
                        case 2 -> viewCompetitor();
                        case 3 -> service.generateReport();
                        case 4 -> System.exit(0);
                        default -> System.out.println("Invalid option");
                    }
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        private static void addCompetitor() throws SQLException {
            System.out.print("ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("First Name: ");
            String firstName = scanner.nextLine();

            System.out.print("Last Name: ");
            String lastName = scanner.nextLine();

            System.out.print("Level: ");
            String level = scanner.nextLine();

            System.out.print("Country: ");
            String country = scanner.nextLine();

            int[] scores = new int[5];
            for (int i = 0; i < 5; i++) {
                System.out.print("Score " + (i + 1) + ": ");
                scores[i] = scanner.nextInt();
            }

            Competitor competitor = new Competitor(id, new Name(firstName, lastName),
                    level, country, scores);
            service.addCompetitor(competitor);
            System.out.println("Competitor added successfully");
        }

        private static void viewCompetitor() {
            System.out.print("Enter competitor ID: ");
            int id = scanner.nextInt();

            service.getCompetitor(id)
                    .ifPresentOrElse(
                            c -> System.out.println(c.getShortDetails()),
                            () -> System.out.println("Competitor not found")
                    );
        }
}
