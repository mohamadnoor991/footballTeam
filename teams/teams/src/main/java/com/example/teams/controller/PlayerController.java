package com.example.teams.controller;

import com.example.teams.model.Player;
import com.example.teams.model.Team;
import com.example.teams.service.PlayerService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/players")
public class PlayerController {

    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @Operation(summary = "Get all players")
    @GetMapping("/all")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    @Operation(summary = "Get Player by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable Long id) {
        return playerService.getPlayerDetails(id)
                .map(ResponseEntity::ok) // Return 200 OK if player found
                .orElse(ResponseEntity.notFound().build()); // Return 404 if not found
    }
}
