package com.example.prestamos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetallePokemonDTO {

    private int weight;
    private String name;
    private PokemonImages sprites;

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PokemonImages getSprites() {
        return sprites;
    }

    public void setSprites(PokemonImages sprites) {
        this.sprites = sprites;
    }
}
