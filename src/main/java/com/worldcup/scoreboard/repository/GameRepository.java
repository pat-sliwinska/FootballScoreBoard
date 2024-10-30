package com.worldcup.scoreboard.repository;

import com.worldcup.scoreboard.model.Game;

import java.util.List;

public interface GameRepository {

    void addGame(Game game);
    void updateGameScore(Game game, int homeScore, int awayScore);
    void removeGame(Game game);
    List<Game> getGames();
}
