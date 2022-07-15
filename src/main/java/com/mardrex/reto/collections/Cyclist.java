package com.mardrex.reto.collections;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "cyclists")
@Getter
@Setter
@Builder(toBuilder = true)
public class Cyclist {

    @Id
    private String id;

    @Indexed(unique=true)
    private String name;

    @Indexed(unique=true)
    private Integer numberId;

    private Team team;

    private String country;

}
