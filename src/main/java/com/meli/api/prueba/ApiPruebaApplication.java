package com.meli.api.prueba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class ApiPruebaApplication {
	private static JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ApiPruebaApplication.class, args);
	}

}
