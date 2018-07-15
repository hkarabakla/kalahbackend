package com.hkarabakla.kalahbackend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Score {

    private Integer firstPlayerScore;
    private Integer secondPlayerScore;
    private Long winner;
}
