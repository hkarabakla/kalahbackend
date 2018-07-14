package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.model.Attacker;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
class NextAttackerIdentifierCommand extends Command {

    @Override
    void execute(Game game, Player player, Integer pitNo) {

        if(player.getKalahPitOrderNumber().equals(game.getBoard().getLastPitNo())) {
            game.setAttacker(Attacker.builder()
                    .attackerId(player.getId())
                    .reason("You lucky ! It's again your turn").build());
        } else if (player.getId().equals(game.getPlayerOne().getId())) {
            game.setAttacker(Attacker.builder()
                    .attackerId(game.getPlayerTwo().getId())
                    .reason("It's your turn").build());
        } else {
            game.setAttacker(Attacker.builder()
                    .attackerId(game.getPlayerOne().getId())
                    .reason("It's your turn").build());
        }
    }
}
