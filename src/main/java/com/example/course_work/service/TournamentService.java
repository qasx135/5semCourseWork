package com.example.course_work.service;

import com.example.course_work.repository.TournamentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.course_work.models.Tournament;

import java.util.List;

@Service
public class TournamentService {

    @Autowired
    private TournamentRepository tournamentRepository;

    public List<Tournament> getAllTournaments() {
        return tournamentRepository.findAll();
    }

    public Tournament getTournamentById(Long id) {
        return tournamentRepository.findById(id).orElse(null);
    }

    public Tournament addTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Tournament updateTournament(Long id, Tournament tournamentDetails) {
        Tournament tournament = tournamentRepository.findById(id).orElse(null);
        if (tournament != null) {
            tournament.setName(tournamentDetails.getName());
            tournament.setLocation(tournamentDetails.getLocation());
            return tournamentRepository.save(tournament);
        }
        return null;
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }
}
