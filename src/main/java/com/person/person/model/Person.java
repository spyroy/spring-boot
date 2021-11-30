package com.person.person.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.jfr.Name;


import java.util.UUID;

public class Person {

    private final UUID id;
    private final String name;


    public Person(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) throws Exception {
        this.id = id;
        this.name = name;
        if(name == null || name.trim().isEmpty()){
            throw new Exception("name must be not blank");
        }
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
