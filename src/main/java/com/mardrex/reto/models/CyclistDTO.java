package com.mardrex.reto.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.mardrex.reto.collections.Team;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Setter
@Builder(toBuilder = true)
public class CyclistDTO {

    private String id;
    @NotBlank(message = "Nombre es requerido")
    private String name;

    private Integer numberId;

    @JsonProperty("team")
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
