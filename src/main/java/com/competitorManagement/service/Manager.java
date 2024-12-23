package com.competitorManagement.service;

import com.competitorManagement.entity.Competitor;

import java.sql.*;
import java.util.*;

public class Manager {
    private final CompetitorService competitorService;

    public Manager(CompetitorService competitorService) {
        this.competitorService = competitorService;
    }

    public void addCompetitor(Competitor competitor) throws SQLException {
        competitorService.addCompetitor(competitor);
    }

    public Optional<Competitor> getCompetitorById(int id) {
        return competitorService.getCompetitor(id);
    }

    public void generateReport() {
        competitorService.generateReport();
    }

    public List<Competitor> getAllCompetitors() {
        return competitorService.getAllCompetitors();
    }
}
