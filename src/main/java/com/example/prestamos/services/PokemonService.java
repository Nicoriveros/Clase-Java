package com.example.prestamos.services;

import com.example.prestamos.dto.DetallePokemonDTO;
import com.example.prestamos.dto.PokemonDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class PokemonService {


    public  PokemonDTO Todos(){
        try {
            //Armar el cliente
            HttpClient client = HttpClient.newHttpClient();
            //Armar la petición
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon/"))
                    .GET()
                    .build();

            //Enviar la petición
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta http: "  + response.statusCode());
            System.out.println("Cuerpo de la respuesta http: "  + response.body());

            //Me permite crear un objeto para convertir un json a un objeto.
            ObjectMapper mapper = new ObjectMapper();
            PokemonDTO pokemons = mapper.readValue(response.body(), PokemonDTO.class);
            System.out.println(pokemons.getCount());
            return pokemons;
        }
        catch (Exception ex){
            System.out.println("Error realizando la petición" + ex.getMessage());
            return null;
        }
    }




    public  DetallePokemonDTO Detalle(String nombre){
        try {
            //Armar el cliente
            HttpClient client = HttpClient.newHttpClient();
            //Armar la petición
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://pokeapi.co/api/v2/pokemon/" + nombre))
                    .GET()
                    .build();

            //Enviar la petición
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            System.out.println("Código de respuesta http: "  + response.statusCode());
            System.out.println("Cuerpo de la respuesta http: "  + response.body());

            String json = response.body();
            String newJson = json.replace("official-artwork","official_artwork");

            //Me permite crear un objeto para convertir un json a un objeto.
            ObjectMapper mapper = new ObjectMapper();
            DetallePokemonDTO pokemons = mapper.readValue(newJson, DetallePokemonDTO.class);
            return pokemons;
        }
        catch (Exception ex){
            System.out.println("Error realizando la petición" + ex.getMessage());
            return null;
        }
    }

}
