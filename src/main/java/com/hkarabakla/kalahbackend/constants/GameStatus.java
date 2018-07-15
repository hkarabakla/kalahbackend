package com.hkarabakla.kalahbackend.constants;

public enum GameStatus {

    WAITING_FOR_FIRST_PLAYER("Waiting for first player to join"),
    WAITING_FOR_SECOND_PLAYER("Waiting for second player to join"),
    WAITING_FOR_FIRST_PLAYERS_ATTACK("Waiting for first player's attack"),
    WAITING_FOR_SECOND_PLAYERS_ATTACK("Waiting for second player's attack"),
    KALAH_REPLAY_CHANCE("KALAH !!! Replay chance"),
    END_OF_THE_GAME("Win Win Win !!!");

    private final String value;

    GameStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
