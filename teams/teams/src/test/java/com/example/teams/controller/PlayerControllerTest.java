package com.example.teams.controller;

import com.example.teams.model.Player;
import com.example.teams.service.PlayerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(PlayerController.class)
public class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    public void testGetAllPlayers() throws Exception {
        Player player1 = new Player("Forward", "Lionel Messi",  10L);
        Player player2 = new Player( "Forward","Cristiano Ronaldo",  7L);

        Mockito.when(playerService.getAllPlayers()).thenReturn(Arrays.asList(player1, player2));

        mockMvc.perform(get("/api/players/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Lionel Messi")))
                .andExpect(jsonPath("$[1].name", is("Cristiano Ronaldo")));
    }

    @Test
    public void testGetPlayerById_Found() throws Exception {
        Player player = new Player(  "Forward","Lionel Messi", 10L);

        Mockito.when(playerService.getPlayerDetails(1L)).thenReturn(Optional.of(player));

        mockMvc.perform(get("/api/players/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Lionel Messi")))
                .andExpect(jsonPath("$.position", is("Forward")));
    }

    @Test
    public void testGetPlayerById_NotFound() throws Exception {
        Mockito.when(playerService.getPlayerDetails(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/players/99"))
                .andExpect(status().isNotFound());
    }
}
