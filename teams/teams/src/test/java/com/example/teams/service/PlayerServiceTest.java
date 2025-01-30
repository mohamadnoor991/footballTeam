package com.example.teams.service;

import com.example.teams.model.Player;
import com.example.teams.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    private Player player1;
    private Player player2;

    @BeforeEach
    public void setUp() {
        player1 = new Player();
        player1.setId(1L);
        player1.setName("Lionel Messi");
        player1.setPosition("Forward");

        player2 = new Player();
        player2.setId(2L);
        player2.setName("Cristiano Ronaldo");
        player2.setPosition("Forward");
    }

    @Test
    public void testGetAllPlayers() {
        // Arrange: Mocking repository response
        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        // Act: Calling the service method
        List<Player> players = playerService.getAllPlayers();

        // Assert: Validating results
        assertNotNull(players);
        assertEquals(2, players.size());
        assertEquals("Lionel Messi", players.get(0).getName());
        assertEquals("Cristiano Ronaldo", players.get(1).getName());

        // Verify interaction with the repository
        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testGetPlayerDetails_WhenPlayerExists() {
        // Arrange
        Long playerId = 1L;
        when(playerRepository.findById(playerId)).thenReturn(Optional.of(player1));

        // Act
        Optional<Player> player = playerService.getPlayerDetails(playerId);

        // Assert
        assertTrue(player.isPresent());
        assertEquals("Lionel Messi", player.get().getName());

        // Verify interaction
        verify(playerRepository, times(1)).findById(playerId);
    }

    @Test
    public void testGetPlayerDetails_WhenPlayerDoesNotExist() {
        // Arrange
        Long playerId = 99L;
        when(playerRepository.findById(playerId)).thenReturn(Optional.empty());

        // Act
        Optional<Player> player = playerService.getPlayerDetails(playerId);

        // Assert
        assertFalse(player.isPresent());

        // Verify interaction
        verify(playerRepository, times(1)).findById(playerId);
    }
}
