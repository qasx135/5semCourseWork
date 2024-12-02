package com.example.course_work.repository;

import com.example.course_work.models.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

// Аннотация JUnit 5 для интеграции Spring TestContext Framework
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class TeamRepositoryTest {

    // Внедряем TeamRepository
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void testSaveAndFindById() {
        // Создаем новую команду
        Team team = new Team();
        team.setName("Real Madrid");

        // Сохраняем команду в базе данных
        Team savedTeam = teamRepository.save(team);

        // Проверяем, что команда была успешно сохранена
        assertThat(savedTeam).isNotNull();
        assertThat(savedTeam.getId()).isNotNull();

        // Находим команду по ID
        Optional<Team> foundTeam = teamRepository.findById(savedTeam.getId());

        // Проверяем, что команда найдена и данные совпадают
        assertThat(foundTeam).isPresent();
        assertThat(foundTeam.get().getName()).isEqualTo("Real Madrid");
    }
}