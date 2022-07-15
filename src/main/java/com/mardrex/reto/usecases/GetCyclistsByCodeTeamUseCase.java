package com.mardrex.reto.usecases;

import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsCyclist;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Objects;


@Service
@Validated
public class GetCyclistsByCodeTeamUseCase {


    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;


    public GetCyclistsByCodeTeamUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;
    }

    public Flux<CyclistDTO> apply(String code) {
        Objects.requireNonNull(code, "Codigo de Team requerido");
        return cyclistRepository.findAllByTeamCode(code)
                .map(mapperUtilsCyclist::mapperToCyclistDTO)
                .onErrorResume(e -> Mono.error(new IllegalArgumentException("No se encontro el Team con el Id: " + code)));
    }
}
