package com.meli.api.prueba.controllers;

import com.meli.api.prueba.utils.Constantes;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductControllerTests {

	@InjectMocks
	private ProductController productController;
	@Mock
	private RestTemplate restTemplate;
	@Test
	public void testListadoProducts() {
		// Arrange
		String site = "MLA";
		String category = "MLA5725";
		String url = Constantes.URLMELI + "/sites/" + site + "/categories";
		String expectedResponse = "{\"site_id\":\"";
		Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<String> response = productController.listadoProducto(site,category);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains(expectedResponse));
	}

	@Test
	public void testListadoProductsEmpty() {
		// Arrange
		String site = "";
		String category = "MLA5725";
		String url = Constantes.URLMELI + "/sites/" + site + "/categories";
		String expectedResponse = "\"message\":\"Si quieres conocer los recursos de la API que se encuentran disponibles visita el Sitio de Desarrolladores de MercadoLibre (http://developers.mercadolibre.com)\"";
		Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<String> response = productController.listadoProducto(site,category);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertTrue(response.getBody().contains(expectedResponse));
	}

}
