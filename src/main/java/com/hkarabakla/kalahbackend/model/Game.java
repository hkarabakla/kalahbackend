package com.hkarabakla.kalahbackend.model;

import com.hkarabakla.kalahbackend.constants.GameConstants;
import com.hkarabakla.kalahbackend.exception.ForbiddenOperationException;
import com.hkarabakla.kalahbackend.util.GameUtil;
import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Game {

    @Id
    @GeneratedValue
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    private Board board;

    @OneToOne(cascade = CascadeType.ALL)
    private Player playerOne;

    @OneToOne(cascade = CascadeType.PERSIST)
    private Player playerTwo;

    @Setter
    @Embedded
    private Attacker attacker;


    public void addPlayer() {

        if (this.playerOne != null && this.playerTwo != null) {
            throw new ForbiddenOperationException("There is no place for new player for this game. Please try other ones.");
        }

        if (this.playerOne == null) {
            List<Pit> pitsForPlayerOne = GameUtil.getPitsForPlayer(GameConstants.PLAYER_ONE);
            this.playerOne = Player.builder()
                    .kalahPitOrderNumber(7)
                    .pitIndexes(pitsForPlayerOne.stream().map(Pit::getOrderOnTheBoard).collect(Collectors.toList()))
                    .build();
            this.board = Board.builder()
                    .pits(pitsForPlayerOne)
                    .build();
        } else {
            List<Pit> pitsForPlayerTwo = GameUtil.getPitsForPlayer(GameConstants.PLAYER_TWO);
            this.playerTwo = Player.builder()
                    .kalahPitOrderNumber(14)
                    .pitIndexes(pitsForPlayerTwo
                            .stream()
                            .map(Pit::getOrderOnTheBoard)
                            .collect(Collectors.toList()))
                    .build();
            board.getPits().addAll(pitsForPlayerTwo);
        }
    }
}
