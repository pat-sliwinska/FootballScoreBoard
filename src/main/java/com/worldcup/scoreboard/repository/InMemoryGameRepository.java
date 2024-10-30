package com.worldcup.scoreboard.repository;

import com.worldcup.scoreboard.model.Game;

import java.util.ArrayList;
import java.util.List;

public class InMemoryGameRepository implements GameRepository {
    private final List<Game> games;

    public InMemoryGameRepository() {
        this.games = new ArrayList<>();
    }

    @Override
    public void addGame(Game game) {

    }

    @Override
    public void updateGameScore(Game game, int homeScore, int awayScore) {

    }

    @Override
    public void removeGame(Game game) {

    }

    @Override
    public List<Game> getGames() {
        return List.of();
    }
}
