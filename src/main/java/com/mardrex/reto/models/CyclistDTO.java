package com.mardrex.reto.models;

import com.mardrex.reto.collections.Team;

import javax.validation.constraints.NotBlank;

public class CyclistDTO {

    private String id;
    @NotBlank(message = "Nombre es requerido")
    private String name;
    @NotBlank(message = "Numero de Identificacion es requerido")
    private Integer numberId;
    @NotBlank(message = "Equipo es requerido")
    private TeamDTO team;
    @NotBlank(message = "Pais es requerido")
    private String country;

    public CyclistDTO() {
    }

    public CyclistDTO(String id, String name, Integer numberId, TeamDTO team, String country) {
        this.id = id;
        this.name = name;
        this.numberId = numberId;
        this.team = team;
        this.country = country;
    }

    @Override
    public String toString() {
        return "CyclistDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", numberId=" + numberId +
                ", team=" + team +
                ", country='" + country + '\'' +
                '}';
    }

}
