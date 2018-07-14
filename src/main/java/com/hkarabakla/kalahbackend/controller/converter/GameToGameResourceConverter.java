package com.hkarabakla.kalahbackend.controller.converter;

import com.hkarabakla.kalahbackend.controller.GameController;
import com.hkarabakla.kalahbackend.controller.resource.AttackerResource;
import com.hkarabakla.kalahbackend.controller.resource.GameResource;
import com.hkarabakla.kalahbackend.controller.resource.PlayerResource;
import com.hkarabakla.kalahbackend.controller.resource.StatusResource;
import com.hkarabakla.kalahbackend.model.Game;
import com.hkarabakla.kalahbackend.model.Pit;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Component
public class GameToGameResourceConverter implements Converter<Game, GameResource> {
    @Override
    public GameResource convert(Game source) {

        GameResource gameResource = GameResource.builder()
                .identifier(source.getId())
                .status(StatusResource.builder()
                        .player1(source.getPlayerOne() != null ? PlayerResource.builder()
                                .id(source.getPlayerOne().getId())
                                .pits(source
                                        .getBoard()
                                        .getPits()
                                        .stream()
                                        .filter(pit -> source
                                                .getPlayerOne()
                                                .getPitIndexes()
                                                .contains(pit.getOrderOnTheBoard()))
                                        .collect(Collectors.toList())
                                        .stream()
                                        .collect(Collectors.toMap(Pit::getOrderOnTheBoard, Pit::getStones)))
                                .build() : null)
                        .player2(source.getPlayerTwo() != null ? PlayerResource.builder()
                                .id(source.getPlayerTwo().getId())
                                .pits(source
                                        .getBoard()
                                        .getPits()
                                        .stream()
                                        .filter(pit -> source
                                                .getPlayerTwo()
                                                .getPitIndexes()
                                                .contains(pit.getOrderOnTheBoard()))
                                        .collect(Collectors.toList())
                                        .stream()
                                        .collect(Collectors.toMap(Pit::getOrderOnTheBoard, Pit::getStones)))
                                .build() : null)
                        .build())
                .attacker(source.getAttacker() != null ? AttackerResource.builder()
                        .attackerId(source.getAttacker().getAttackerId())
                        .reason(source.getAttacker().getReason())
                        .build() : null)
                .build();

        gameResource.add(linkTo(methodOn(GameController.class).get(source.getId())).withSelfRel());
        return gameResource;
    }
}
