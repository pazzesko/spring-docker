package com.example.springdocker.persistence;

import javax.validation.constraints.NotEmpty;

public class PokemonDTO {

    @NotEmpty(message = "The name of the pokemon cannot be empty")
    private String name;
    @NotEmpty(message = "The type of the pokemon cannot be empty")
    private String type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
