package com.example.teams.controller;

import com.example.teams.model.Team;
import com.example.teams.service.TeamService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TeamController.class)
public class TeamControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TeamService teamService;

    private Team team;

    @BeforeEach
    void setUp() {
        team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        team.setAcronym("TT");
        team.setBudget(BigDecimal.valueOf(1000000.0));
    }

    // ✅ Test GET /api/teams/all (Get all teams)
    @Test
    public void testGetAllTeams() throws Exception {
        when(teamService.getAllTeams()).thenReturn(List.of(team));

        mockMvc.perform(get("/api/teams/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].name").value("Test Team"))
                .andExpect(jsonPath("$[0].acronym").value("TT"));
    }

    // ✅ Test GET /api/teams/{id} (Get team by ID)
    @Test
    public void testGetTeamById_Found() throws Exception {
        when(teamService.getTeamDetails(1L)).thenReturn(Optional.of(team));

        mockMvc.perform(get("/api/teams/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.name").value("Test Team"))
                .andExpect(jsonPath("$.acronym").value("TT"));
    }

    @Test
    public void testGetTeamById_NotFound() throws Exception {
        when(teamService.getTeamDetails(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/teams/99"))
                .andExpect(status().isNotFound());
    }

    // ✅ Test POST /api/teams (Create team)
    @Test
    public void testCreateTeam() throws Exception {
        when(teamService.createTeam(any(Team.class))).thenReturn(team);

        String jsonRequest = """
            {
                "name": "Test Team",
                "acronym": "TT",
                "budget": 1000000.0
            }
        """;

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonRequest))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Test Team"))
                .andExpect(jsonPath("$.acronym").value("TT"))
                .andExpect(jsonPath("$.budget").value(1000000.0));
    }

    // ✅ Test DELETE /api/teams/{id} (Delete team)
    @Test
    public void testDeleteTeam_Success() throws Exception {
        doNothing().when(teamService).deleteById(1L);

        mockMvc.perform(delete("/api/teams/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Team with ID 1 deleted successfully."));
    }

    @Test
    public void testDeleteTeam_NotFound() throws Exception {
        doThrow(new RuntimeException("Team with ID 99 not found.")).when(teamService).deleteById(99L);

        mockMvc.perform(delete("/api/teams/99"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Team with ID 99 not found."));
    }
}
