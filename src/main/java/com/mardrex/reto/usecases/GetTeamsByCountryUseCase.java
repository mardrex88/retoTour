package com.mardrex.reto.usecases;

import com.mardrex.reto.models.TeamDTO;
import com.mardrex.reto.repositories.TeamRepository;
import com.mardrex.reto.usecases.utils.MapperUtilsTeam;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import reactor.core.publisher.Flux;

@Service
@Validated
public class GetTeamsByCountryUseCase {

    private final TeamRepository teamRepository;
    private final MapperUtilsTeam mapperUtilsTeam;

    public GetTeamsByCountryUseCase(TeamRepository teamRepository, MapperUtilsTeam mapperUtilsTeam) {
        this.teamRepository = teamRepository;
        this.mapperUtilsTeam = mapperUtilsTeam;
    }

    public Flux<TeamDTO> apply(String country) {
        return teamRepository.findByCountry(country)
                .map(mapperUtilsTeam::mapperToTeamDTO);
    }
}
