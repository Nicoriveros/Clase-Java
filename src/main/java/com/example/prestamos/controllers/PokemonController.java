package com.example.prestamos.controllers;

import com.example.prestamos.dto.DetallePokemonDTO;
import com.example.prestamos.dto.PokemonDTO;
import com.example.prestamos.entities.User;
import com.example.prestamos.services.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;

@Controller
public class PokemonController extends  BaseController {

    @Autowired
    private PokemonService pokemonService;

    @GetMapping("pokemon/consulta")
    public String consulta(Model data, @AuthenticationPrincipal OidcUser principal){
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
        PokemonDTO pokemons = pokemonService.Todos();
        data.addAttribute("pokemons",pokemons);
        return  "pokemon/consulta";
    }

    @GetMapping("pokemon/detalle/{nombre}")
    public  String detalle(@PathVariable String nombre,Model data, @AuthenticationPrincipal OidcUser principal){
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
        data.addAttribute("nombrepokemon",nombre);
        DetallePokemonDTO detalle = this.pokemonService.Detalle(nombre);
        data.addAttribute("detalle",detalle);

        String imagenBuena = detalle.getSprites().getOther().getOfficial_artwork().getFront_default();
        imagenBuena = imagenBuena.replace("official_artwork","official-artwork");
        data.addAttribute("imagenbuena",imagenBuena);
        return  "pokemon/detalle";
    }

}
