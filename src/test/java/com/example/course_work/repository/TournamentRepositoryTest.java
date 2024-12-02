package com.example.course_work.repository;

import com.example.course_work.models.Tournament;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TournamentRepositoryTest {

    @Autowired
    private TournamentRepository tournamentRepository;

    @Test
    public void testSaveAndFindById() {
        // Создаем новый турнир
        Tournament tournament = new Tournament();
        tournament.setName("Champions League");

        // Сохраняем турнир в базе данных
        Tournament savedTournament = tournamentRepository.save(tournament);

        // Проверяем, что турнир был успешно сохранен
        assertThat(savedTournament).isNotNull();
        assertThat(savedTournament.getId()).isNotNull();

        // Находим турнир по ID
        Optional<Tournament> foundTournament = tournamentRepository.findById(savedTournament.getId());

        // Проверяем, что турнир найден и данные совпадают
        assertThat(foundTournament).isPresent();
        assertThat(foundTournament.get().getName()).isEqualTo("Champions League");
    }
}
