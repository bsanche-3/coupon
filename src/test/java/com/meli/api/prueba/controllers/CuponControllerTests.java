package com.meli.api.prueba.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.meli.api.prueba.models.ReqCoupon;
import com.meli.api.prueba.utils.Constantes;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CuponControllerTests {

	@InjectMocks
	private CuponController couponController;
	@Mock
	private RestTemplate restTemplate;

	@Test
	public void testListadoPriceProducts() throws Exception {
		// Crear un objeto ReqCoupon para simular una solicitud de cliente
		ReqCoupon reqCoupon = new ReqCoupon();
		reqCoupon.setItem_ids(new String[] {"MPE624617921"});
		reqCoupon.setAmount(2000.0);

		// Crear un objeto ObjectMapper para simular la conversión de JSON a objeto Java
		ObjectMapper objectMapper = new ObjectMapper();
		ObjectNode itemNode = objectMapper.createObjectNode();
		itemNode.put("id", "MPE624617921");
		itemNode.put("price", 2000.0);
		ArrayNode jsonProducts = objectMapper.createArrayNode();
		jsonProducts.add(itemNode);

		// Llamar al método y verificar que la respuesta sea la esperada
		ResponseEntity<String> result = couponController.listadoPriceProducts(reqCoupon);
		assertEquals(HttpStatus.OK, result.getStatusCode());
		JSONObject jsonResult = new JSONObject(result.getBody());
		assertEquals(720, jsonResult.getDouble("total"), 0.001);
		//assertArrayEquals(new String[] {"MPE624617921"}, jsonResult.getJSONArray("item_ids").toList().toArray(new String[0]));
		String[] itemIds = objectMapper.readValue(jsonResult.getJSONArray("item_ids").toString(), String[].class);
		assertArrayEquals(new String[] {"MPE624617921"}, itemIds);
	}

	@Test
	public void testListadoPriceProductsEmpty() throws Exception {
		// Crear un objeto ReqCoupon para simular una solicitud de cliente
		ReqCoupon reqCoupon = new ReqCoupon();
		reqCoupon.setItem_ids(new String[] {""});
		reqCoupon.setAmount(2000.0);

		// Llamar al método y verificar que la respuesta sea la esperada
		ResponseEntity<String> result = couponController.listadoPriceProducts(reqCoupon);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		assertTrue(result.getBody().contains(Constantes.FALLA));
	}

	@Test
	public void testListadoPriceProductsItemError() throws Exception {
		// Crear un objeto ReqCoupon para simular una solicitud de cliente
		ReqCoupon reqCoupon = new ReqCoupon();
		reqCoupon.setItem_ids(new String[] {"MP624617921","MP624617944"});
		reqCoupon.setAmount(2000.0);

		// Llamar al método y verificar que la respuesta sea la esperada
		ResponseEntity<String> result = couponController.listadoPriceProducts(reqCoupon);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		assertTrue(result.getBody().contains(Constantes.FALLA));
	}

	@Test
	public void testListadoPriceProductsSumaInvEmpty() throws Exception {
		// Crear un objeto ReqCoupon para simular una solicitud de cliente
		ReqCoupon reqCoupon = new ReqCoupon();
		reqCoupon.setItem_ids(new String[] {"MPE624617921","MPE624617921"});
		reqCoupon.setAmount(20.0);

		// Llamar al método y verificar que la respuesta sea la esperada
		ResponseEntity<String> result = couponController.listadoPriceProducts(reqCoupon);
		assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
		assertTrue(result.getBody().contains(Constantes.FALLA2));
	}
}
