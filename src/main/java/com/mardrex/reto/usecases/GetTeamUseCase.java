package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Team;
import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.repositories.CyclistRepository;
import com.mardrex.reto.repositories.TeamRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsCyclist;
import com.mardrex.reto.usecases.utils.MapperUtilsTeam;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Function;

@Service
@Validated
public class GetTeamUseCase {

    private final TeamRepository teamRepository;
    private final MapperUtilsTeam mapperUtilsTeam;
    private final CyclistRepository cyclistRepository;
    private final MapperUtilsCyclist mapperUtilsCyclist;


    public GetTeamUseCase(TeamRepository teamRepository, MapperUtilsTeam mapperUtilsTeam, CyclistRepository cyclistRepository, MapperUtilsCyclist mapperUtilsCyclist) {
        this.teamRepository = teamRepository;
        this.mapperUtilsTeam = mapperUtilsTeam;
        this.cyclistRepository = cyclistRepository;
        this.mapperUtilsCyclist = mapperUtilsCyclist;
    }

    public Mono<TeamDTO> aplly(String id){
        Objects.requireNonNull(id, "Es obligatorio el Id del Team para poder realizar una consulta");
        Mono<Team> fallBack = Mono.error(new IllegalArgumentException("No se encontro el Team con el Id: " + id));
        return teamRepository.findById(id)
                .switchIfEmpty(fallBack)
                .map(mapperUtilsTeam::mapperToTeamDTO)
            //    .flatMap(mapTeamWithCyclists())
                .onErrorResume(e -> Mono.error(new IllegalArgumentException("No se encontro el Team con el Id: " + id)));
    }

    private Function<TeamDTO, Mono<TeamDTO>> mapTeamWithCyclists(){
        return teamDTO ->
                Mono.just(teamDTO).zipWith(
                        cyclistRepository.findAllByTeamId(teamDTO.getId())
                                .map(mapperUtilsCyclist::mapperToCyclistDTO)
                                .collectList(),
                        (team, cyclist) -> {
                            team.setCyclists(cyclist);
                            return team;
                        }
                );
    }
}
