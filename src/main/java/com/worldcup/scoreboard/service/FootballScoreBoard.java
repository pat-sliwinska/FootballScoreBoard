package com.worldcup.scoreboard.service;

import com.worldcup.scoreboard.model.Game;
import com.worldcup.scoreboard.repository.GameRepository;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FootballScoreBoard implements ScoreBoard {
    private final GameRepository gameRepository;

    public FootballScoreBoard(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game startGame(String homeTeam, String awayTeam) {
        Game game = new Game(homeTeam, awayTeam);
        gameRepository.addGame(game);
        return game;
    }

    @Override
    public void finishGame(Game game) {
        gameRepository.removeGame(game);
    }

    @Override
    public void updateScore(Game game, int homeScore, int awayScore) {
        gameRepository.updateGameScore(game, homeScore, awayScore);
    }

    @Override
    public List<Game> getSummary() {
        return gameRepository.getGames().stream()
                .sorted(Comparator
                        .comparingInt(Game::getTotalScore)
                        .thenComparing(Game::getCreatedAt).reversed())
                .collect(Collectors.toList());
    }
}

