package com.competitorManagement.service;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.repository.CompetitorRepository;

import java.sql.*;
import java.util.*; //List, Optional

public class CompetitorService {
    private final CompetitorRepository repository;

    public CompetitorService(CompetitorRepository repository) {
        this.repository = repository;
    }

    public void addCompetitor(Competitor competitor) throws SQLException {
        validateScores(competitor.getScores());
        repository.save(competitor);
    }

    public Optional<Competitor> getCompetitor(int id) {
        return repository.findById(id);
    }

    public void generateReport() {
        List<Competitor> competitors = repository.findAll();

        System.out.println("\n=== Competition Report ===");
        System.out.println("\nAll Competitors:");
        System.out.println("ID\tName\tLevel\tCountry\tScores\tAverage");
        competitors.forEach(c -> System.out.println(c.getFullDetails()));

        Optional<Competitor> topPerformer = competitors.stream()
                .max(Comparator.comparingDouble(Competitor::getOverallScore));

        System.out.println("\nTop Performer:");
        topPerformer.ifPresent(c -> System.out.println(c.getFullDetails()));

        System.out.println("\nScore Distribution:");
        Map<Integer, Integer> scoreFrequency = new HashMap<>();
        competitors.forEach(c -> {
            for (int score : c.getScores()) {
                scoreFrequency.merge(score, 1, Integer::sum);
            }
        });

        for (int i = 0; i <= 5; i++) {
            System.out.printf("Score %d: %d times%n", i, scoreFrequency.getOrDefault(i, 0));
        }
    }

    private void validateScores(int[] scores) {
        if (scores.length != 5) {
            throw new IllegalArgumentException("Must have exactly 5 scores");
        }
        //Validate scores range from user input
//        for (int score : scores) {
//            if (score < 0 || score > 5) {
//                throw new IllegalArgumentException("Scores must be between 0 and 5");
//            }
//        }
    }
}
