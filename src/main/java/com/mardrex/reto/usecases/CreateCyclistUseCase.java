package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Cyclist;
import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;
import javax.validation.Valid;
import java.util.Collections;


@Service
@Validated
public class CreateCyclistUseCase {

    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;


    public CreateCyclistUseCase(CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;
    }

    public Mono<String> apply(@Valid CyclistDTO cyclistDTO) {

          return cyclistRepository.getCountByTeamName(cyclistDTO.getTeam().getName())
                   .count()
                   .flatMap(count -> {
                        if (count > 7) {
                            return Mono.error(new IllegalArgumentException("El aquipo ya tiene 8 ciclistas"));
                        } else {
                            return cyclistRepository.save(mapperUtilsCyclist.mapperToCyclist(cyclistDTO));
                        }
                    })
                    .map(Cyclist::getId);
    }


}
