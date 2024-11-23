package com.example.course_work.controller;

import com.example.course_work.service.TournamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.course_work.models.Tournament;

import java.util.List;

@RestController
@RequestMapping("/api/tournaments")
public class TournamentController {

    @Autowired
    private TournamentService tournamentService;

    @GetMapping
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @GetMapping("/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id);
    }

    @PostMapping
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.addTournament(tournament);
    }

    @PutMapping("/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournamentDetails) {
        return tournamentService.updateTournament(id, tournamentDetails);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
        return ResponseEntity.noContent().build();
    }
}
