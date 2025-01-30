package com.example.teams.service;

import com.example.teams.model.Team;
import com.example.teams.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    private Team team1, team2;

    @BeforeEach
    void setUp() {
        team1 = new Team(1L, "Team A", "TMA", BigDecimal.valueOf(50000.0), null);
        team2 = new Team(2L, "Team B", "TMB", BigDecimal.valueOf(7000000.0), null);
    }

    @Test
    void testGetTeams() {
        int page = 0, size = 2;
        String sortBy = "name";
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Team> mockPage = new PageImpl<>(Arrays.asList(team1, team2));

        when(teamRepository.findAll(pageable)).thenReturn(mockPage);

        Page<Team> result = teamService.getTeams(page, size, sortBy);

        assertEquals(2, result.getContent().size());
        assertEquals("Team A", result.getContent().get(0).getName());
        verify(teamRepository, times(1)).findAll(pageable);
    }

    @Test
    void testCreateTeam() {
        when(teamRepository.save(team1)).thenReturn(team1);

        Team savedTeam = teamService.createTeam(team1);

        assertNotNull(savedTeam);
        assertEquals("Team A", savedTeam.getName());
        verify(teamRepository, times(1)).save(team1);
    }

    @Test
    void testGetTeamDetails_WhenExists() {
        when(teamRepository.findById(1L)).thenReturn(Optional.of(team1));

        Optional<Team> result = teamService.getTeamDetails(1L);

        assertTrue(result.isPresent());
        assertEquals("Team A", result.get().getName());
        verify(teamRepository, times(1)).findById(1L);
    }

    @Test
    void testGetTeamDetails_WhenNotExists() {
        when(teamRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Team> result = teamService.getTeamDetails(99L);

        assertFalse(result.isPresent());
        verify(teamRepository, times(1)).findById(99L);
    }

    @Test
    void testDeleteById() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteById(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetAllTeams() {
        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));

        List<Team> result = teamService.getAllTeams();

        assertEquals(2, result.size());
        assertEquals("Team A", result.get(0).getName());
        verify(teamRepository, times(1)).findAll();
    }
}
