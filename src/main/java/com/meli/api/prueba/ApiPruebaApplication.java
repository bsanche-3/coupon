package com.meli.api.prueba;

import com.meli.api.prueba.utils.Conectar;
import com.meli.api.prueba.utils.SqlServices;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.SQLException;

@SpringBootApplication
public class ApiPruebaApplication {
	private static JdbcTemplate jdbcTemplate;

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(ApiPruebaApplication.class, args);

		String result;
		System.out.println("Servidor Iniciado");

		Conectar conectar = new Conectar();
		SqlServices sqlServices = new SqlServices();
		jdbcTemplate= conectar.conectar();
		sqlServices.probarConexion(jdbcTemplate);
	}

}
