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

    private String status;
    private Long attackerId;
    private Score score;
}
