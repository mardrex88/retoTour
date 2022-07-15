package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Team;
import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.repositories.TeamRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsTeam;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Service
@Validated
public class GetTeamUseCase {

    private final TeamRepository teamRepository;
    private final MapperUtilsTeam mapperUtilsTeam;


    public GetTeamUseCase(TeamRepository teamRepository, MapperUtilsTeam mapperUtilsTeam) {
        this.teamRepository = teamRepository;
        this.mapperUtilsTeam = mapperUtilsTeam;

    }

    public Mono<TeamDTO> aplly(String id) {
        Objects.requireNonNull(id, "Es obligatorio el Id del Team para poder realizar una consulta");
        Mono<Team> fallBack = Mono.error(new IllegalArgumentException("No se encontro el Team con el Id: " + id));
        return teamRepository.findById(id)
                .switchIfEmpty(fallBack)
                .map(mapperUtilsTeam::mapperToTeamDTO)
                //    .flatMap(mapTeamWithCyclists())
                .onErrorResume(e -> Mono.error(new IllegalArgumentException("No se encontro el Team con el Id: " + id)));
    }

}
