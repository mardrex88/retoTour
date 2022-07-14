package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Team;
import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.repositories.TeamRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@Service
@Validated
public class CreateTeamUseCase {

    private final TeamRepository teamRepository;
    private final MapperUtilsTeam mapperUtilsTeam;

    public CreateTeamUseCase(TeamRepository teamRepository, MapperUtilsTeam mapperUtilsTeam) {
        this.teamRepository = teamRepository;
        this.mapperUtilsTeam = mapperUtilsTeam;
    }

    public Mono<String> apply(@Valid TeamDTO teamDTO) {
        return teamRepository
                .save(mapperUtilsTeam.mapperToTeam(teamDTO))
                .map(Team::getId);
    }
}
