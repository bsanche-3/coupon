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
class SitesControllerTests {

	@InjectMocks
	private SitesController sitesController;
	@Mock
	private RestTemplate restTemplate;
	@Test
	public void testListadoProducts() {
		// Arrange
		String url = Constantes.URLMELI + "/sites/";
		String expectedResponse = "{sites:[{\"default_currency_id\":";
		Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<String> response = sitesController.listadoSite();

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertTrue(response.getBody().contains(expectedResponse));
	}
}
