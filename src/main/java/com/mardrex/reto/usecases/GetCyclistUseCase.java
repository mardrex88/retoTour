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
public class GetCyclistUseCase {

    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;

    public GetCyclistUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;
    }

    public Mono<CyclistDTO> apply(String id) {
        Objects.requireNonNull(id, "Id is required");
        Mono<Cyclist> fallBack = Mono.error(new IllegalArgumentException("No se encontro el Ciclista con el Id: " + id));
        return cyclistRepository.findById(id)
                .switchIfEmpty(fallBack)
                .map(mapperUtilsCyclist::mapperToCyclistDTO);
    }
}
