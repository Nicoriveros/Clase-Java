package com.example.prestamos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonOtherDTO {

    private ImagenBuena official_artwork;


    public ImagenBuena getOfficial_artwork() {
        return official_artwork;
    }

    public void setOfficial_artwork(ImagenBuena official_artwork) {
        this.official_artwork = official_artwork;
    }
}


