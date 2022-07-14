package com.mardrex.reto.models;

import com.mardrex.reto.collections.Cyclist;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.List;

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
