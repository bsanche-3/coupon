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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CategoriesControllerTests {

	@InjectMocks
	private CategoriesController categoriesController;
	@Mock
	private RestTemplate restTemplate;
	@Test
	public void testListadoCategorie() {
		// Arrange
		String site = "MLA";
		String url = Constantes.URLMELI + "/sites/" + site + "/categories";
		String expectedResponse = "{categories:[{\"id\":\"MLA5725\",\"name\":\"Accesorios para Vehículos\"},{\"id\":\"MLA1512\",\"name\":\"Agro\"},{\"id\":\"MLA1403\",\"name\":\"Alimentos y Bebidas\"},{\"id\":\"MLA1071\",\"name\":\"Animales y Mascotas\"},{\"id\":\"MLA1367\",\"name\":\"Antigüedades y Colecciones\"},{\"id\":\"MLA1368\",\"name\":\"Arte, Librería y Mercería\"},{\"id\":\"MLA1743\",\"name\":\"Autos, Motos y Otros\"},{\"id\":\"MLA1384\",\"name\":\"Bebés\"},{\"id\":\"MLA1246\",\"name\":\"Belleza y Cuidado Personal\"},{\"id\":\"MLA1039\",\"name\":\"Cámaras y Accesorios\"},{\"id\":\"MLA1051\",\"name\":\"Celulares y Teléfonos\"},{\"id\":\"MLA1648\",\"name\":\"Computación\"},{\"id\":\"MLA1144\",\"name\":\"Consolas y Videojuegos\"},{\"id\":\"MLA1500\",\"name\":\"Construcción\"},{\"id\":\"MLA1276\",\"name\":\"Deportes y Fitness\"},{\"id\":\"MLA5726\",\"name\":\"Electrodomésticos y Aires Ac.\"},{\"id\":\"MLA1000\",\"name\":\"Electrónica, Audio y Video\"},{\"id\":\"MLA2547\",\"name\":\"Entradas para Eventos\"},{\"id\":\"MLA407134\",\"name\":\"Herramientas\"},{\"id\":\"MLA1574\",\"name\":\"Hogar, Muebles y Jardín\"},{\"id\":\"MLA1499\",\"name\":\"Industrias y Oficinas\"},{\"id\":\"MLA1459\",\"name\":\"Inmuebles\"},{\"id\":\"MLA1182\",\"name\":\"Instrumentos Musicales\"},{\"id\":\"MLA3937\",\"name\":\"Joyas y Relojes\"},{\"id\":\"MLA1132\",\"name\":\"Juegos y Juguetes\"},{\"id\":\"MLA3025\",\"name\":\"Libros, Revistas y Comics\"},{\"id\":\"MLA1168\",\"name\":\"Música, Películas y Series\"},{\"id\":\"MLA1430\",\"name\":\"Ropa y Accesorios\"},{\"id\":\"MLA409431\",\"name\":\"Salud y Equipamiento Médico\"},{\"id\":\"MLA1540\",\"name\":\"Servicios\"},{\"id\":\"MLA9304\",\"name\":\"Souvenirs, Cotillón y Fiestas\"},{\"id\":\"MLA1953\",\"name\":\"Otras categorías\"}]}";
		Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<String> response = categoriesController.listadoCategorie(site);

		// Assert
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedResponse, response.getBody());
	}

	@Test
	public void testListadoCategorieEmpty() {
		// Arrange
		String site = "";
		String url = Constantes.URLMELI + "/sites/" + site + "/categories";
		String expectedResponse = Constantes.CATEGEMPTY;
		Mockito.when(restTemplate.getForObject(url, String.class)).thenReturn(expectedResponse);

		// Act
		ResponseEntity<String> response = categoriesController.listadoCategorie(site);

		// Assert
		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(expectedResponse, response.getBody());
	}
}
