package com.mardrex.reto.models;

import com.mardrex.reto.collections.Cyclist;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import javax.validation.constraints.NotBlank;
import java.util.List;


@Getter
@Setter
@Builder(toBuilder = true)
public class TeamDTO {

    private String id;
    @NotBlank(message = "Nombre es requerido")
    private String name;
    @NotBlank(message = "Codigo de Pais es requerido")
    private String code;
    @NotBlank(message = "Pais es requerido")
    private String country;

    private List<CyclistDTO> cyclists;
}
