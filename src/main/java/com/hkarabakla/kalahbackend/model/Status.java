package com.hkarabakla.kalahbackend.model;

import com.hkarabakla.kalahbackend.constants.GameStatus;
import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Status {

    @Enumerated(value = EnumType.STRING)
    private GameStatus status;
    private Long attackerId;
    private Integer firstPlayerScore;
    private Integer secondPlayerScore;
    private Long winner;
}
