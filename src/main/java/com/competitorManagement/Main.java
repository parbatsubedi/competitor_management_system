package com.competitorManagement;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.entity.Name;
import com.competitorManagement.repository.CompetitorRepository;
import com.competitorManagement.service.CompetitorService;
import com.competitorManagement.service.Manager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final CompetitorService competitorService = new CompetitorService(new CompetitorRepository());
    private static final Manager manager = new Manager(competitorService);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n1. Add competitor");
            System.out.println("2. View competitor");
            System.out.println("3. Generate report");
            System.out.println("4. View all competitors");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> addCompetitor();
                    case 2 -> viewCompetitor();
                    case 3 -> manager.generateReport();
                    case 4 -> viewAllCompetitors();
                    case 5 -> System.exit(0);
                    default -> System.out.println("Invalid option. Please try again.");
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

        Competitor competitor = new Competitor(id, new Name(firstName, lastName), level, country, scores);
        manager.addCompetitor(competitor);
        System.out.println("Competitor added successfully.");
    }

    private static void viewCompetitor() {
        System.out.print("Enter competitor ID: ");
        int id = scanner.nextInt();

        Optional<Competitor> competitor = manager.getCompetitorById(id);
        if (competitor.isPresent()) {
            System.out.println(competitor.get().getShortDetails());
        } else {
            System.out.println("Competitor not found.");
        }
    }

    private static void viewAllCompetitors() {
        List<Competitor> competitors = manager.getAllCompetitors();
        if (competitors.isEmpty()) {
            System.out.println("No competitors found.");
        } else {
            competitors.forEach(c -> System.out.println(c.getFullDetails()));
        }
    }
}
