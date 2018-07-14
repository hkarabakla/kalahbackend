package com.hkarabakla.kalahbackend.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue
    private Long id;

    private Integer lastPitNo;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Pit> pits;

}
