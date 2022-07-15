package com.mardrex.reto.usecases;

import com.mardrex.reto.collections.Cyclist;
import com.mardrex.reto.collections.Team;
import com.mardrex.reto.models.CyclistDTO;
import com.mardrex.reto.models.TeamDTO;
import org.springframework.stereotype.Component;

@Component
public class MapperUtilsCyclist {

    public CyclistDTO mapperToCyclistDTO (Cyclist cyclist) {
        return CyclistDTO.builder()
                .id(cyclist.getId())
                .name(cyclist.getName())
                .numberId(cyclist.getNumberId())
                .team(getTeamDTO(cyclist.getTeam()))
                .build();
    }

    public Cyclist mapperToCyclist (CyclistDTO cyclistDTO) {
        return Cyclist.builder()
                .id(cyclistDTO.getId())
                .name(cyclistDTO.getName())
                .numberId(cyclistDTO.getNumberId())
                .team(getTeam(cyclistDTO.getTeam()))
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

    private Team getTeam(TeamDTO teamDTO) {
        return Team.builder()
                .id(teamDTO.getId())
                .name(teamDTO.getName())
                .code(teamDTO.getCode())
                .country(teamDTO.getCountry())
                .build();
    }
}
