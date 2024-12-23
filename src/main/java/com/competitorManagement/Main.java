package com.competitorManagement;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.entity.Name;

public class Main {
    public static void main(String[] args) {

        Name name1 = new Name("Alice", "Green");
        Competitor competitor1 = new Competitor(200, name1, "Beginner", "USA");

        Name name2 = new Name("Bob", "Brown");
        Competitor competitor2 = new Competitor(201, name2, "Intermediate", "Canada");

        //Display Input datas in console
        System.out.println("Input Data:");
        System.out.println("Competitor 1: ID=" + competitor1.getCompetitorID() + ", Name=" + competitor1.getName() + ", Level=" + competitor1.getLevel() + ", Country=" + competitor1.getCountry());
        System.out.println("Competitor 2: ID=" + competitor2.getCompetitorID() + ", Name=" + competitor2.getName() + ", Level=" + competitor2.getLevel() + ", Country=" + competitor2.getCountry());

        // Display Results
        System.out.println("\nResults:");
        System.out.println(competitor1.getFullDetails());
        System.out.println(competitor1.getShortDetails());

        System.out.println(competitor2.getFullDetails());
        System.out.println(competitor2.getShortDetails());
    }
}