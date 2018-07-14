package com.hkarabakla.kalahbackend.command;

import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Player;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class Command {

    private Command next;

    abstract void execute(Game game, Player player, Integer pitNo);
}
