package com.mardrex.reto.usecases;

import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsCyclist;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;


@Service
@Validated
public class ListCyclistUseCase {

    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;


    public ListCyclistUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;
    }

    public Flux<CyclistDTO> get(){
        return cyclistRepository.findAll()
                .map(mapperUtilsCyclist::mapperToCyclistDTO);
    }

}
