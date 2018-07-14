package com.hkarabakla.kalahbackend.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Pit {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @Column(length = 36)
    private String id;

    private Integer orderOnTheBoard;
    private Integer stones;
    private Boolean kalah;

    public void addStone(int count) {
        this.stones += count;
    }

    public void removeAllStones() {
        this.stones = 0;
    }
}
