package com.mardrex.reto.collections;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
@Getter
@Setter
@Builder(toBuilder = true)
public class Team {

    @Id
    private String id;
    private String name;
    private String code;
    private String country;
    private List<Cyclist> cyclists;

}
