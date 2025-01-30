package com.example.teams.service;

import com.example.teams.model.Team;
import com.example.teams.repository.TeamRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Page<Team> getTeams(int page, int size, String sortBy) {
        return teamRepository.findAll(PageRequest.of(page, size, Sort.by(sortBy)));
    }


    public Team createTeam(Team team) {
        return teamRepository.save(team);
    }

    public Optional<Team> getTeamDetails(Long id) {
        return teamRepository.findById(id);
    }

    public void deleteById(Long id) {
        teamRepository.deleteById(id);
    }

    // Get all teams with their full details (not paginated)
    public List<Team> getAllTeams() {
        return teamRepository.findAll(); // This retrieves all teams with associated players.
    }
}

