package com.competitorManagement.entity;

public class Competitor {
    private int competitorID;
    private Name name;
    private String level; // Beginner, Intermediate, Advanced
    private String country; // Extra attribute
    private int[] scores; // Array of scores

    public Competitor(int competitorID, Name name, String level, String country, int[] scores) {
        this.competitorID = competitorID;
        this.name = name;
        this.level = level;
        this.country = country;
        this.scores = scores;
    }

    public int getCompetitorID() {
        return competitorID;
    }

    public void setCompetitorID(int competitorID) {
        this.competitorID = competitorID;
    }

    public Name getName() {
        return name;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int[] getScores() {
        return scores;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }

    public double getOverallScore() {
        if (scores == null || scores.length == 0) return 0;

        // Calculate average excluding highest and lowest scores
        int total = 0, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;

        for (int score : scores) {
            total += score;
            if (score > max) max = score;
            if (score < min) min = score;
        }

        total -= (max + min);
        return scores.length > 2 ? (double) total / (scores.length - 2) : (double) total / scores.length;
    }

    public String getFullDetails() {
        return "Competitor number " + competitorID + ", name " + name + ", country " + country + ".\n" +
                name.getFirstName() + " is a " + level + " and has an overall score of " + getOverallScore() + ".";
    }

    public String getShortDetails() {
        return "CN " + competitorID + " (" + name.getInitials() + ") has overall score " + getOverallScore() + ".";
    }
}
