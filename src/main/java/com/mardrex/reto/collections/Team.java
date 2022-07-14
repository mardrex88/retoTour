package com.mardrex.reto.collections;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Document
@Getter
@Setter
@Builder(toBuilder = true)
public class Team {

    private String id;
    private String name;
    private String code;
    private String country;

}
