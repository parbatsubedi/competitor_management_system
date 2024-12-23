package com.competitorManagement.service;

import com.competitorManagement.entity.Competitor;
import com.competitorManagement.repository.CompetitorRepository;

import java.util.List;

public class CompetitorList {
    private final CompetitorRepository competitorRepository;

    public CompetitorList(CompetitorRepository competitorRepository) {
        this.competitorRepository = competitorRepository;
    }

    public List<Competitor> getAllCompetitors() {
        return competitorRepository.findAll();
    }
}
