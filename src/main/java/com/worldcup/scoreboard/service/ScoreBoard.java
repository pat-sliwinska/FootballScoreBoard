package com.worldcup.scoreboard.service;

import com.worldcup.scoreboard.model.Game;

import java.util.List;

public interface ScoreBoard {
    Game startGame(String homeTeam, String awayTeam);
    void finishGame(Game game);
    void updateScore(Game game, int homeScore, int awayScore);
    List<Game> getSummary();
}