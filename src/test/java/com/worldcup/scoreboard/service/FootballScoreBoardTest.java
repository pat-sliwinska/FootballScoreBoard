package com.worldcup.scoreboard.service;

import com.worldcup.scoreboard.exception.GameNotExistException;
import com.worldcup.scoreboard.model.Game;
import com.worldcup.scoreboard.repository.GameRepository;
import com.worldcup.scoreboard.repository.InMemoryGameRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FootballScoreBoardTest {

    private GameRepository gameRepository;
    private FootballScoreBoard footballScoreBoard;

    @BeforeEach
    void setUp() {
        gameRepository = new InMemoryGameRepository();
        footballScoreBoard = new FootballScoreBoard(gameRepository, new ScoreBoardValidator());
    }

    @Test
    void shouldStartGame() {
        // given
        Game game = footballScoreBoard.startGame("A", "B");

        // then
        List<Game> games = gameRepository.getGames();
        assertEquals(1, games.size());

        assertEquals("A", game.getHomeTeam());
        assertEquals("B", game.getAwayTeam());
        assertEquals(0, game.getHomeScore());
        assertEquals(0, game.getAwayScore());
    }

    @Test
    void shouldThrowExceptionWhenHomeTeamIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.startGame("", "Canada"));
    }

    @Test
    void shouldThrowExceptionWhenAwayTeamIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.startGame("Mexico", ""));
    }

    @Test
    void shouldThrowExceptionWhenHomeTeamIsNull() {
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.startGame(null, "Canada"));
    }

    @Test
    void shouldThrowExceptionWhenAwayTeamIsNull() {
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.startGame("Mexico", null));
    }

    @Test
    void shouldThrowExceptionWhenTeamsAreDuplicate() {
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.startGame("Mexico", "Mexico"));
    }

    @Test
    void shouldFinishGame() {
        // given
        Game game = footballScoreBoard.startGame("A", "B");

        // when
        footballScoreBoard.finishGame(game);

        // then
        List<Game> games = gameRepository.getGames();
        assertTrue(games.isEmpty());
    }

    @Test
    void shouldThrowExceptionWhenFinishingNonExistingGame() {
        // given
        Game nonExistingGame = new Game("Mexico", "Canada");

        // when, then
        assertThrows(GameNotExistException.class, () -> footballScoreBoard.finishGame(nonExistingGame));
    }

    @Test
    void shouldThrowExceptionWhenFinishingAlreadyFinishedGame() {
        // given
        Game game = footballScoreBoard.startGame("Mexico", "Canada");

        // when
        footballScoreBoard.finishGame(game);

        // then
        assertThrows(GameNotExistException.class, () -> footballScoreBoard.finishGame(game));
    }


    @Test
    void shouldUpdateScore() {
        // given
        Game game = footballScoreBoard.startGame("A", "B");

        // when
        footballScoreBoard.updateScore(game, 3, 2);

        // then
        assertEquals(3, game.getHomeScore());
        assertEquals(2, game.getAwayScore());
    }

    @Test
    void shouldThrowExceptionWhenHomeScoreIsNegative() {
        // given
        Game game = footballScoreBoard.startGame("Mexico", "Canada");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.updateScore(game, -1, 3));
    }

    @Test
    void shouldThrowExceptionWhenAwayScoreIsNegative() {
        // given
        Game game = footballScoreBoard.startGame("Mexico", "Canada");

        // when, then
        assertThrows(IllegalArgumentException.class, () -> footballScoreBoard.updateScore(game, 3, -1));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingScoreForNonExistingGame() {
        // given
        Game nonExistingGame = new Game("Mexico", "Canada");

        // when, then
        assertThrows(GameNotExistException.class, () -> footballScoreBoard.updateScore(nonExistingGame, 1, 1));
    }

    @Test
    void shouldThrowExceptionWhenUpdatingScoreAfterGameIsFinished() {
        // given
        Game game = footballScoreBoard.startGame("Mexico", "Canada");

        //when
        footballScoreBoard.finishGame(game);

        // then
        assertThrows(GameNotExistException.class, () -> footballScoreBoard.updateScore(game, 1, 1));
    }


    @Test
    void shouldGetSummaryOrderedByTotalScoreAndCreationDate() {
        // given
        Game mexicoCanadaGame = footballScoreBoard.startGame("Mexico", "Canada");
        footballScoreBoard.updateScore(mexicoCanadaGame, 0, 5);

        Game spainBrazilGame = footballScoreBoard.startGame("Spain", "Brazil");
        footballScoreBoard.updateScore(spainBrazilGame, 10, 2);

        Game germanyFranceGame = footballScoreBoard.startGame("Germany", "France");
        footballScoreBoard.updateScore(germanyFranceGame, 2, 2);

        Game uruguayItalyGame = footballScoreBoard.startGame("Uruguay", "Italy");
        footballScoreBoard.updateScore(uruguayItalyGame, 6, 6);

        Game argentinaAustraliaGame = footballScoreBoard.startGame("Argentina", "Australia");
        footballScoreBoard.updateScore(argentinaAustraliaGame, 3, 1);

        // when
        List<Game> summary = footballScoreBoard.getSummary();

        // then
        assertEquals(5, summary.size());
        assertEquals("Uruguay", summary.get(0).getHomeTeam());
        assertEquals("Spain", summary.get(1).getHomeTeam());
        assertEquals("Mexico", summary.get(2).getHomeTeam());
        assertEquals("Argentina", summary.get(3).getHomeTeam());
        assertEquals("Germany", summary.get(4).getHomeTeam());
    }

    @Test
    void shouldReturnEmptySummaryWhenNoGamesStarted() {
        // when
        List<Game> summary = footballScoreBoard.getSummary();

        //then
        assertTrue(summary.isEmpty());
    }

    @Test
    void shouldOrderGamesWithSameTotalScoreByMostRecentFirst() {
        // given
        Game game1 = footballScoreBoard.startGame("Team A", "Team B");
        footballScoreBoard.updateScore(game1, 2, 2);
        Game game2 = footballScoreBoard.startGame("Team C", "Team D");
        footballScoreBoard.updateScore(game2, 1, 3);
        Game game3 = footballScoreBoard.startGame("Team E", "Team F");
        footballScoreBoard.updateScore(game3, 3, 1);

        // when
        List<Game> summary = footballScoreBoard.getSummary();

        // then
        assertEquals(List.of(game3, game2, game1), summary);
    }
}
