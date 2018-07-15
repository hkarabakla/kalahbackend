package com.hkarabakla.kalahbackend.controller.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.ResourceSupport;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GameResource extends ResourceSupport {

    @JsonProperty(value = "id")
    private Long identifier;

    private PlayerResource first;
    private PlayerResource second;
    private StatusResource status;
}
