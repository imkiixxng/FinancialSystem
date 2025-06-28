package com.example.finance.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LikeItemAddRequest {
    private String userId;
    private String productName;
    private BigDecimal price;
    private BigDecimal feeRate;
    private int orderAmount;
    private String account;
}

