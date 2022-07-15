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
public class UpdateTeamUseCase {

    private final TeamRepository teamRepository;
    private final MapperUtilsTeam mapperUtilsTeam;


    public UpdateTeamUseCase(TeamRepository teamRepository, MapperUtilsTeam mapperUtilsTeam) {
        this.teamRepository = teamRepository;
        this.mapperUtilsTeam = mapperUtilsTeam;
    }

    public Mono<String> apply( TeamDTO teamDto) {
        Objects.requireNonNull(teamDto.getId(), "TeamDto is required");
        return teamRepository.findById(teamDto.getId())
                .map(team -> mapperUtilsTeam.mapperToTeam(teamDto))
                .flatMap(teamRepository::save)
                .map(Team::getId);
    }
}
