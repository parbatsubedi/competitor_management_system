package com.competitorManagement.entity;

public class Competitor {
    private int competitorID;
    private Name name;
    private String level;
    private String country;

    public Competitor(int competitorID, Name name, String level, String country) {
        this.competitorID = competitorID;
        this.name = name;
        this.level = level;
        this.country = country;
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

    public double getOverallScore() {
        return 5;
    }

    public String getFullDetails() {
        return "Competitor number " + competitorID + ", name " + name + ", country " + country + ".\n" +
                name.getFirstName() + " is a " + level + " and has an overall score of " + getOverallScore() + ".";
    }

    public String getShortDetails() {
        return "CN " + competitorID + " (" + name.getInitials() + ") has overall score " + getOverallScore() + ".";
    }
}
