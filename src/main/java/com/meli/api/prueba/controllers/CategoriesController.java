package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class CategoriesController {

    @GetMapping("/categories")
    public ResponseEntity<String> listadoCategorie(@RequestParam String site) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constantes.URLMELI + "/sites/" + site + "/categories";
        String response;
        try {
            response ="{categories:" + restTemplate.getForObject(url,String.class) + "}";
        }catch (HttpClientErrorException ehttp){
            return ResponseEntity.badRequest().body(Constantes.CATEGEMPTY);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
