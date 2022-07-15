package com.mardrex.reto.collections;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "teams")
@Getter
@Setter
@Builder(toBuilder = true)
public class Team {

    @Id
    private String id;

    @Indexed(unique=true)
    private String name;
    @Indexed(unique=true)
    private String code;
    private String country;

}
