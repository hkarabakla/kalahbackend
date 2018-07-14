package com.hkarabakla.kalahbackend.controller.resource;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PlayerResource {

    private Long id;
    private Map<Integer, Integer> pits = new HashMap<>();
}
