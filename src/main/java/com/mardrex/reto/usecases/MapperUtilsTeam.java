package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Team;
import com.mardrex.reto.models.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperUtilsTeam {

    public TeamDTO mapperToTeamDTO(Team team) {

        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .code(team.getCode())
                .country(team.getCountry())
                .build();
    }

    public Team mapperToTeam(TeamDTO teamDTO) {

        return Team.builder()
                .id(teamDTO.getId())
                .name(teamDTO.getName())
                .code(teamDTO.getCode())
                .country(teamDTO.getCountry())
                .build();
    }

    private TeamDTO getTeamDTO(Team team) {

        return TeamDTO.builder()
                .id(team.getId())
                .name(team.getName())
                .code(team.getCode())
                .country(team.getCountry())
                .build();
    }

}
