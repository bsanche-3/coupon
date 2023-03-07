package com.meli.api.prueba.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class SqlServices {

    @Autowired
    public SqlServices() { }

    public String findTopFavorities(JdbcTemplate jdbcTemplate, String[] siteId) {
        String sql = "SELECT TOP 5 item_id, conteo FROM favoritos where site_id = ? ORDER BY conteo desc FOR JSON AUTO";
        return jdbcTemplate.queryForObject(sql, siteId, String.class);
    }

    public void mergeFavorities(JdbcTemplate jdbcTemplate, String itemId, String siteId) {
        String sql = "MERGE INTO favoritos USING (SELECT ? AS item_id, ? AS site_id, 1 AS conteo) AS reg ON favoritos.item_id = reg.item_id WHEN MATCHED THEN UPDATE SET site_id = reg.site_id, conteo = favoritos.conteo + reg.conteo WHEN NOT MATCHED THEN INSERT(item_id , site_id ,conteo) VALUES (reg.item_id , reg.site_id, reg.conteo);";
        jdbcTemplate.update(sql, itemId,siteId);
    }
}
