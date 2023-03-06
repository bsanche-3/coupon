package com.meli.api.prueba.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SitesControllerTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void listadoSiteTotal(){
		ResponseEntity<String> listadoSite = SitesController.listadoSite();
	}
}
