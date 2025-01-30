package com.example.teams.service;

import com.example.teams.model.Player;
import com.example.teams.model.Team;
import com.example.teams.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    // Get all players with their full details (not paginated)
    public List<Player> getAllPlayers() {
        return playerRepository.findAll(); // This retrieves all teams with associated players.
    }

    public Optional<Player> getPlayerDetails(Long id) {
        return playerRepository.findById(id);
    }
}
