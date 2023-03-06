package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Constantes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api")
public class SitesController {

    @GetMapping("/sites")
    public static ResponseEntity<String> listadoSite() {
        RestTemplate restTemplate = new RestTemplate();
        String url = Constantes.URLMELI + "/sites/";
        String response;
        try {
            response ="{sites:" + restTemplate.getForObject(url,String.class) + "}";
        }catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }
}
