package com.worldcup.scoreboard.service;

import com.worldcup.scoreboard.model.Game;
import com.worldcup.scoreboard.repository.GameRepository;

import java.util.List;

public class FootballScoreBoard implements ScoreBoard {
    private final GameRepository gameRepository;

    public FootballScoreBoard(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public Game startGame(String homeTeam, String awayTeam) {
        return null;
    }

    @Override
    public void finishGame(Game game) {

    }

    @Override
    public void updateScore(Game game, int homeScore, int awayScore) {

    }

    @Override
    public List<Game> getSummary() {
        return List.of();
    }
}

