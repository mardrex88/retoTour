package com.mardrex.reto.usecases;

import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsCyclist;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetCyclistsByCountryUseCase {

    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;

    public GetCyclistsByCountryUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;

    }

    public Flux<CyclistDTO> apply(String country) {
        return cyclistRepository.findByCountry(country)
                .map(mapperUtilsCyclist::mapperToCyclistDTO);

    }
}
