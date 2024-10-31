package com.worldcup.scoreboard.exception;

public class GameNotExistException extends RuntimeException {
    public GameNotExistException(String message) {
        super(message);
    }
}
