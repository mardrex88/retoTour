package com.mardrex.reto.usecases;

import com.mardrex.reto.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class DeleteTeamUseCase {

    private final TeamRepository teamRepository;

    public DeleteTeamUseCase(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return teamRepository.deleteById(id);
    }
}
