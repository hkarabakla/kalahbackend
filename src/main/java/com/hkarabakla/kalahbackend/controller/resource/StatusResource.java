package com.hkarabakla.kalahbackend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class StatusResource {

    private PlayerResource player1;
    private PlayerResource player2;
}
