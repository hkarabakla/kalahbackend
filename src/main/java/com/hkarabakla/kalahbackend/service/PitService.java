package com.hkarabakla.kalahbackend.service;

import com.hkarabakla.kalahbackend.model.Pit;
import com.hkarabakla.kalahbackend.repository.PitRepository;
import org.springframework.stereotype.Service;

@Service
public class PitService {

    private final PitRepository pitRepository;

    public PitService(PitRepository pitRepository) {
        this.pitRepository = pitRepository;
    }

    public Pit save(Pit pit) {
        return pitRepository.save(pit);
    }
}
