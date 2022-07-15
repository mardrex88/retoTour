package com.mardrex.reto.usecases;


import com.mardrex.reto.collections.Cyclist;
import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsCyclist;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class UpdateCyclistUseCase {

    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;


    public UpdateCyclistUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;

    }

    public Mono<String> apply( CyclistDTO cyclistDTO) {
        Objects.requireNonNull(cyclistDTO.getId(), "TeamDto is required");
        return cyclistRepository.findById(cyclistDTO.getId())
                .map(team -> mapperUtilsCyclist.mapperToCyclist(cyclistDTO))
                .flatMap(cyclistRepository::save)
                .map(Cyclist::getId);
    }
}
