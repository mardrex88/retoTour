package com.mardrex.reto.usecases;

import com.mardrex.reto.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class DeleteCyclistUseCase {

    private final CyclistRepository cyclistRepository;

    public DeleteCyclistUseCase(CyclistRepository cyclistRepository) {
        this.cyclistRepository = cyclistRepository;
    }

    public Mono<Void> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        return cyclistRepository.deleteById(id);
    }

}
