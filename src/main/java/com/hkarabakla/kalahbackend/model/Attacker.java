package com.hkarabakla.kalahbackend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Attacker {

    private Long attackerId;
    private String reason;
}
