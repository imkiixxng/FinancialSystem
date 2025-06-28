package com.example.finance.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LikeItemUpdateRequest {
    private Integer sn;
    private BigDecimal price;
    private BigDecimal feeRate;
    private String account;
    private Integer orderAmount;
}
