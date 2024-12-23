package com.competitorManagement.entity;

import java.util.Arrays;

public class Competitor {
    private int competitorID;
    private Name name;
    private String level;
    private String country;
    private int[] scores;

    public Competitor(int competitorID, Name name, String level, String country, int[] scores) {
        this.competitorID = competitorID;
        this.name = name;
        this.level = level;
        this.country = country;
        this.scores = scores;
    }

    // Getters
    public int getCompetitorID() { return competitorID; }
    public Name getName() { return name; }
    public String getLevel() { return level; }
    public String getCountry() { return country; }
    public int[] getScores() { return scores; }

    public double getOverallScore() {
        return Arrays.stream(scores).average().orElse(0.0);
    }

    public String getShortDetails() {
        return String.format("ID: %d, Name: %s %s, Level: %s",
                competitorID, name.getFirstName(), name.getLastName(), level);
    }

    public String getFullDetails() {
        return String.format("%d\t%s %s\t%s\t%s\t%s\t%.1f",
                competitorID, name.getFirstName(), name.getLastName(), level, country,
                Arrays.toString(scores), getOverallScore());
    }
}
