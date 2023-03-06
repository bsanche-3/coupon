package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class ProductController {

    @GetMapping("/Products")
    public ResponseEntity<String> listadoProducto(@RequestParam String site, @RequestParam String category) {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constantes.URLMELI + "/sites/"+site+"/search?category="+category;
        String response;
        try {
            response = restTemplate.getForObject(url,String.class);
        }catch (HttpServerErrorException ehttp){
            return ResponseEntity.badRequest().body(Constantes.FALLA);
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

}