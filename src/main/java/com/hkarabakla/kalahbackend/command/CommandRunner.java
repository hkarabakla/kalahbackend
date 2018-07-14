package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CommandRunner {

    public void run(Game game, Player player, Integer pitNo) {
        Command nextAttackerIdentifierCommand = new NextAttackerIdentifierCommand();
        Command stoneCollectorCommand = new StoneCollectorCommand();
        Command playerCommand = new PlayerCommand();

        playerCommand.setNext(stoneCollectorCommand);
        stoneCollectorCommand.setNext(nextAttackerIdentifierCommand);
        playerCommand.execute(game, player, pitNo);
    }
}
