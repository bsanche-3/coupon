package com.meli.api.prueba.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class Conectar {

    @Autowired
    public JdbcTemplate conectar() {
        DbConfig dbConfig = new DbConfig();
        return new JdbcTemplate(dbConfig.dataSource());
    }

}
