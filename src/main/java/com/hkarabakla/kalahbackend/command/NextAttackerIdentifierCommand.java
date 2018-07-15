package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.constants.GameStatus;
import com.hkarabakla.kalahbackend.model.Status;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class NextAttackerIdentifierCommand extends Command {

    @Override
    void execute(Game game, Player player, Integer pitNo) {

        if(player.getKalahPitOrderNumber().equals(game.getBoard().getLastPitNo())) {
            game.setStatus(Status.builder()
                    .attackerId(player.getId())
                    .status(GameStatus.KALAH_REPLAY_CHANCE).build());
        } else if (player.getId().equals(game.getPlayerOne().getId())) {
            game.setStatus(Status.builder()
                    .attackerId(game.getPlayerTwo().getId())
                    .status(GameStatus.WAITING_FOR_SECOND_PLAYERS_ATTACK)
                    .build());
        } else {
            game.setStatus(Status.builder()
                    .attackerId(game.getPlayerOne().getId())
                    .status(GameStatus.WAITING_FOR_FIRST_PLAYERS_ATTACK)
                    .build());
        }

        getNext().execute(game, player, pitNo);
    }
}
