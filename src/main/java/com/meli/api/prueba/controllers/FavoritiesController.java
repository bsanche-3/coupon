package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Conectar;
import com.meli.api.prueba.utils.Constantes;
import com.meli.api.prueba.utils.SqlServices;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class FavoritiesController {

    @GetMapping("/favorities")
    public ResponseEntity<String> listadoFavorities(@RequestParam String site) {
        JdbcTemplate jdbcTemplate;
        String response;
        String[] arrSite = new String[]{site};
        Conectar conectar = new Conectar();
        jdbcTemplate = conectar.conectar();
        SqlServices sqlServices = new SqlServices();
        try {
            response = sqlServices.findTopFavorities(jdbcTemplate,arrSite);
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.badRequest().body(Constantes.BDEMPTY);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().body(response);
    }

}
