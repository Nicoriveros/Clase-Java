package com.example.prestamos.controllers;


import com.example.prestamos.entities.User;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class HomeController extends BaseController {

    @GetMapping("inicio")
    public String inicio(Model data,@AuthenticationPrincipal OidcUser principal){

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
        return "home/inicio";
    }

}
