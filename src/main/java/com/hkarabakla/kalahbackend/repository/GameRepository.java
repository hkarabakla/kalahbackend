package com.hkarabakla.kalahbackend.repository;

import com.hkarabakla.kalahbackend.model.Game;
import org.springframework.data.repository.CrudRepository;


public interface GameRepository extends CrudRepository<Game, Long> {
}
