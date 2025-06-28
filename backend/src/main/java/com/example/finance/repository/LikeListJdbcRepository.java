
package com.example.finance.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public class LikeListJdbcRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void callAddLikeItem(String userId, String productName, BigDecimal price,
                                BigDecimal feeRate, int orderAmount, String account) {
        String sql = "CALL AddLikeItem(?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, userId, productName, price, feeRate, orderAmount, account);
    }
}
