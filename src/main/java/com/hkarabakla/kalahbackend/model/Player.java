package com.hkarabakla.kalahbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Player {

    @Id
    @GeneratedValue
    private Long id;

    @ElementCollection
    @CollectionTable(name = "player_pit_indexes", joinColumns = @JoinColumn(name = "player_id"))
    private List<Integer> pitIndexes = new ArrayList<>();

    private Integer kalahPitOrderNumber;
}
