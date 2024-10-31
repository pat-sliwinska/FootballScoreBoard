package com.worldcup.scoreboard.model;

import java.time.Instant;
import java.util.Objects;

public class Game {
    private final String homeTeam;
    private final String awayTeam;
    private int homeScore;
    private int awayScore;
    private final Instant createdAt;

    public Game(String homeTeam, String awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = 0;
        this.awayScore = 0;
        this.createdAt = Instant.now();
    }

    public int getTotalScore() {
        return homeScore + awayScore;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    @Override
    public String toString() {
        return String.format("%s %d - %s %d", homeTeam, homeScore, awayTeam, awayScore);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return homeScore == game.homeScore
                && awayScore == game.awayScore
                && Objects.equals(homeTeam, game.homeTeam)
                && Objects.equals(awayTeam, game.awayTeam)
                && Objects.equals(createdAt, game.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(homeTeam, awayTeam, homeScore, awayScore, createdAt);
    }
}
