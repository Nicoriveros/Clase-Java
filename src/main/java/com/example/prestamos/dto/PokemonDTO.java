package com.example.prestamos.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokemonDTO {

    private int count;
    private String next;
    private String previous;
    private ArrayList<PokemonNameDTO> results;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public ArrayList<PokemonNameDTO> getResults() {
        return results;
    }

    public void setResults(ArrayList<PokemonNameDTO> results) {
        this.results = results;
    }
}
