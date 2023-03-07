package com.meli.api.prueba.controllers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.meli.api.prueba.models.ReqCoupon;
import com.meli.api.prueba.utils.*;
import com.networknt.exception.ApiException;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;


@RestController
@RequestMapping("/api")
public class CuponController extends ResponseEntityExceptionHandler {

    @PostMapping(value = "/coupon", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<String> listadoPriceProducts(@RequestBody ReqCoupon reqCoupon) {
        RestTemplate restTemplate = new RestTemplate();

        String[] items = reqCoupon.getItem_ids();//llenar con los id necesarios
        if (items.length<1 || items[0].length()<3){
            return ResponseEntity.badRequest().body(Constantes.FALLA);
        }
        double amountCoupon = reqCoupon.getAmount();// valor del coupon

        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode jsonProducts = objectMapper.createArrayNode();

        Map<String, Double> mapItemsCoupon;
        String idSite = items[0].substring(0,3);
        try {
            mapItemsCoupon = recorrerItemCoupon(restTemplate, items, objectMapper, jsonProducts, idSite);
        }catch (Exception e){
            return ResponseEntity.badRequest().body(Constantes.FALLA);
        }

        List<Map.Entry<String, Double>> listItemsCouponSorted = new ArrayList<>(mapItemsCoupon.entrySet());
        listItemsCouponSorted.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        guardarFavoritiesEnviados(mapItemsCoupon,idSite);// todos los enviados

        //---------------------------------------------------------------------------------------

        Double[] prices = new Double[listItemsCouponSorted.size()]; //crear la lista de precios de los items consultados
        Map<String, Double> listResultPrices = new HashMap<>();

        fillPrices(listItemsCouponSorted, prices, listResultPrices);

        ArrayList<Double> arrTargetPrices;
        List<Map.Entry<Double,ArrayList<Double>>> result;

        result = SumInversa.sumInversaUp(new ArrayList<>(Arrays.asList(prices)),amountCoupon);// ejecutar la suma inversa (combinatoria de valores) y retornar ordenado de mayor a menor

        if (result.isEmpty()){
            return ResponseEntity.badRequest().body(Constantes.FALLA2);
        }
        Map.Entry<Double, ArrayList<Double>> dato = result.get(0); // tomamos solo el primer registro ya que es la maximizacion del gasto del cuopon

        double targetCoupon = dato.getKey();
        arrTargetPrices = dato.getValue();
        String[] itemsId= new String[arrTargetPrices.size()];
        fillResults(listResultPrices, arrTargetPrices, itemsId);
        try {
            return ResponseEntity.ok().body(new JSONObject().put("total", targetCoupon).put("item_ids", itemsId).toString());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private Map<String, Double> recorrerItemCoupon(RestTemplate restTemplate, String[] items, ObjectMapper objectMapper, ArrayNode jsonProducts,String idSite) throws Exception {
        String response;
        Map<String, Double> mapItemsCoupon = new HashMap<>();
        for (String item: items) {
            String url = Constantes.URLMELI + "/items/" + item;
            try {
                response = restTemplate.getForObject(url,String.class);
                JsonNode jsonNode = objectMapper.readTree(response);
                if (jsonNode.get("site_id").textValue().equals(idSite)){
                    jsonProducts.add(jsonNode);
                    mapItemsCoupon.put(jsonNode.get("id").textValue(), jsonNode.get("price").doubleValue());
                }
            } catch (Exception e) {
                if (mapItemsCoupon.isEmpty()){
                    if (item.equals(items[0]) && items.length>=2)
                        idSite = items[1].substring(0,3);
                    else
                        throw new Exception(e);
                }
            }
        }
        return mapItemsCoupon;
    }

    private void fillResults(Map<String, Double> listResultPrices, ArrayList<Double> arrTargetPrices, String[] itemsId) {
        String itemId;
        for(int indice = 0; indice< arrTargetPrices.size(); indice++)
        {
            itemId = Utilidades.getKey(listResultPrices, arrTargetPrices.get(indice));
            itemsId[indice] = itemId;
            listResultPrices.remove(itemId);
        }
    }

    private void fillPrices(List<Map.Entry<String, Double>> listItemsCouponSorted, Double[] prices, Map<String, Double> listResultPrices) {
        Map.Entry<String, Double> listprices;
        for(int indice = 0; indice< listItemsCouponSorted.size(); indice++) //recorrer el resultado de los productos con sus precios
        {
            listprices = listItemsCouponSorted.get(indice);
            prices[indice] = listprices.getValue();// llenar la lista de precios
            listResultPrices.put(listprices.getKey(),listprices.getValue());//llenar producto precio en un mapa
        }
    }

    private void guardarFavoritiesEnviados(Map<String, Double> mapItemsCoupon,String siteId) {
        Conectar conectar = new Conectar();
        SqlServices sqlServices = new SqlServices();
        JdbcTemplate jdbcTemplate = conectar.conectar();
        for (Map.Entry<String, Double> mapItemSorted : mapItemsCoupon.entrySet()) {
            sqlServices.mergeFavorities(jdbcTemplate,mapItemSorted.getKey(),siteId);
        }
    }
}
