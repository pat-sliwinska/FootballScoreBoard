package com.worldcup.scoreboard.service;

import com.worldcup.scoreboard.exception.GameNotExistException;
import com.worldcup.scoreboard.model.Game;

import java.util.List;

public class ScoreBoardValidator {

    public void validateTeamNames(String homeTeam, String awayTeam) {
        if (homeTeam == null || awayTeam == null || homeTeam.isEmpty() || awayTeam.isEmpty()) {
            throw new IllegalArgumentException("Team names cannot be null or empty.");
        }
        if (homeTeam.equals(awayTeam)) {
            throw new IllegalArgumentException("Home and away teams cannot be the same.");
        }
    }

    public void validateScores(int homeScore, int awayScore) {
        if (homeScore < 0 || awayScore < 0) {
            throw new IllegalArgumentException("Scores must be non-negative.");
        }
    }

    public void validateGameExists(Game game, List<Game> games) {
        if (!games.contains(game)) {
            throw new GameNotExistException("Game does not exist in the repository.");
        }
    }
}
