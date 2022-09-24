package com.example.prestamos.controllers;


import com.example.prestamos.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @GetMapping("inicio")
    public String inicio(Model data,@AuthenticationPrincipal OidcUser principal){

        User user = new User();
        user = seguridad();
        if(principal != null){
           Map<String,Object> Auth0Data = principal.getClaims();
           String username =(String) Auth0Data.get("name");
           user = this.service.selectByUserName(username);
        }
        else{

        }

        data.addAttribute("usuarioautenticado",user);
        return "home/inicio";
    }


    @GetMapping("consume")
    public String consume(Model data,@AuthenticationPrincipal OidcUser principal) throws IOException, InterruptedException {
        User user = new User();
        if(principal != null){
            Map<String,Object> Auth0Data = principal.getClaims();
            String username =(String) Auth0Data.get("name");
            user = this.service.selectByUserName(username);
        }
        else{
            user = seguridad();
        }

        data.addAttribute("usuarioautenticado",user);

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://pokeapi.co/api/v2/pokemon/"))
                .GET()
                .build();


        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.statusCode());
        System.out.println(response.body());

        /*ObjectMapper mapper = new ObjectMapper();
        PokemonDTO pokemons = mapper.readValue(response.body(), PokemonDTO.class);
        System.out.println("Cantidad de pokemones" + pokemons.getCount());
        data.addAttribute("cantidad",pokemons.getCount());*/
        return "home/consume";
    }

}
