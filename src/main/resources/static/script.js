// Показать вкладку
function showTab(tabId) {
    const tabs = document.querySelectorAll('.tab-content');
    tabs.forEach(tab => {
        tab.style.display = 'none';
    });
    document.getElementById(tabId).style.display = 'block';
}

// Обработка вкладок
document.getElementById('tournaments-tab').addEventListener('click', () => showTab('tournaments'));
document.getElementById('teams-management-tab').addEventListener('click', () => showTab('teams'));
document.getElementById('add-players-tab').addEventListener('click', () => showTab('add-players'));

// Добавить Турнир
document.getElementById('tournament-form').addEventListener('submit', async (e) => {
    e.preventDefault();
    const tournamentData = {
        name: document.getElementById('tournament-name').value,
        location: document.getElementById('tournament-location').value,
        description: document.getElementById('tournament-description').value,
        startDate: document.getElementById('tournament-start-date').value,
        endDate: document.getElementById('tournament-end-date').value
    };

    const response = await fetch('/tournaments', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(tournamentData)
    });

    if (response.ok) {
        alert('Турнир добавлен!');
        document.getElementById('tournament-form').reset();
        loadTournaments();
    } else {
        alert('Ошибка при добавлении турнира.');
    }
});

// Загрузить турниры
async function loadTournaments() {
    const response = await fetch('/tournaments');
    const tournaments = await response.json();

    const tournamentList = document.getElementById('tournament-list');
    tournamentList.innerHTML = '';
    tournaments.forEach(tournament => {
        const div = document.createElement('div');
        console.log("lol,")
        div.textContent = `(${tournament.id}  ${tournament.name} ${tournament.location})`;
        tournamentList.appendChild(div);

        const option = document.createElement('option');
        option.value = tournament.id;
        option.textContent = tournament.name;
        document.getElementById('tournament-select').appendChild(option);
    });
}


// Добавить Команду
document.getElementById('team-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const name = document.getElementById('team-name').value;
    const rating = document.getElementById('team-rating').value;
    const tourid = document.getElementById('tournament-select').value;
    const response_1 = await fetch('/tournaments');
    const tournaments = await response_1.json();

    const tournament = tournaments.find(t => t.id.toString() === tourid)
    if (tournament) {
        console.log('tournament')
    }

    const tournamentId = tournament.id


    const teamData = {
        rating: rating,
        tournament_id: tournamentId,
        name: name
    };

    const response = await fetch('/teams', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(teamData)
    });

    if (response.ok) {
        alert('Команда создана!');
        document.getElementById('team-form').reset();
        loadTeams();
    } else {
        alert('Ошибка при создании команды.');
    }
});

// Загрузить команды для выбранного турнира
async function loadTeams() {
    const tournamentId = document.getElementById('tournament-select').value;
    const response = await fetch(`/teams?tournamentId=${tournamentId}`);
    const teams = await response.json();

    const teamList = document.getElementById('team-list');
    teamList.innerHTML = '';
    teams.forEach(team => {
        const div = document.createElement('div');
        div.textContent = team.name;
        teamList.appendChild(div);

        const option = document.createElement('option');
        option.value = team.id;
        option.textContent = team.name;
        document.getElementById('team-select').appendChild(option);
    });
}

// Добавить Игрока
document.getElementById('player-form').addEventListener('submit', async (e) => {
    e.preventDefault();

    const teamid = document.getElementById('team-select').value;
    console.log(teamid)
    const response_1 = await fetch('/teams');
    const teams = await response_1.json();

    const team = teams.find(t => t.id.toString() === teamid)
    if (team) {
        console.log('team')

    }

    const team_id = team.id

    const playerData = {
        team_id: team_id,
        name: document.getElementById('player-name').value,
        age: document.getElementById('player-age').value
    };

    const response = await fetch('/players', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(playerData)
    });

    if (response.ok) {
        alert('Игрок добавлен!');
        document.getElementById('player-form').reset();
        loadPlayers();
    } else {
        alert('Ошибка при добавлении игрока.');
    }
});

// Загрузить игроков для выбранной команды
async function loadPlayers() {
    const teamId = document.getElementById('team-select').value;
    const response = await fetch(`/players?teamId=${teamId}`);
    const players = await response.json();

    const playerList = document.getElementById('player-list');
    playerList.innerHTML = '';
    players.forEach(player => {
        const div = document.createElement('div');
        div.textContent = player.name;
        playerList.appendChild(div);
    });
}

// При загрузке страницы
window.onload = async () => {
    await loadTournaments();
    await loadTeams();
    await loadPlayers();
};