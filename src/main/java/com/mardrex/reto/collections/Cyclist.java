package com.mardrex.reto.collections;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Getter
@Setter
public class Cyclist {

    @Id
    private String id;
    private String name;
    private Integer numberId;
    private Team team;
    private String country;

}
