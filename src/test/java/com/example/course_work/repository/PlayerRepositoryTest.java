package com.example.course_work.repository;

import com.example.course_work.models.Player;
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
public class PlayerRepositoryTest {

    // Внедряем PlayerRepository
    @Autowired
    private PlayerRepository playerRepository;

    @Test
    public void testSaveAndFindById() {
        // Создаем нового игрока
        Player player = new Player();
        player.setName("Cristiano Ronaldo");

        // Сохраняем игрока в базе данных
        Player savedPlayer = playerRepository.save(player);

        // Проверяем, что игрок был успешно сохранен
        assertThat(savedPlayer).isNotNull();
        assertThat(savedPlayer.getId()).isNotNull();

        // Находим игрока по ID
        Optional<Player> foundPlayer = playerRepository.findById(savedPlayer.getId());

        // Проверяем, что игрок найден и данные совпадают
        assertThat(foundPlayer).isPresent();
        assertThat(foundPlayer.get().getName()).isEqualTo("Cristiano Ronaldo");
    }
}
