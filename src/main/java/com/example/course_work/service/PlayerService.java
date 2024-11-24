package com.example.course_work.service;

import com.example.course_work.models.Player;
import com.example.course_work.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    private PlayerRepository playerRepository;

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player getPlayerById(Long id) {
        return playerRepository.findById(id).orElse(null);
    }

    public Player addPlayer(Player player) {
        return playerRepository.save(player);
    }

    public Player updatePlayer(Long id, Player playerDetails) {
        Player player = playerRepository.findById(id).orElse(null);
        if (player != null) {
            player.setName(playerDetails.getName());
            player.setId(playerDetails.getId());
            player.setAge(playerDetails.getAge());
            player.setTeam_id(playerDetails.getTeam_id());
            return playerRepository.save(player);
        }
        return null;
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
}
