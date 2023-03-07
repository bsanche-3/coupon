package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Constantes;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class FavoritiesControllerTests {

	@Test
	void testListadoFavorities() {
		String site = "MPE";
		FavoritiesController controller = new FavoritiesController();
		ResponseEntity<String> response = controller.listadoFavorities(site);
		assertEquals(200, response.getStatusCodeValue());
		assertTrue(response.getBody().contains("[{\"item_id\":"));
	}

	@Test
	void testErrorSite() {
		String site = "MP";
		FavoritiesController controller = new FavoritiesController();
		ResponseEntity<String> response = controller.listadoFavorities(site);
		assertEquals(400, response.getStatusCodeValue());
		assertTrue(response.getBody().contains(Constantes.BDEMPTY));
	}
}
