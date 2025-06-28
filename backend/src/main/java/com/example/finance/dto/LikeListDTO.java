package com.example.finance.dto;

import java.math.BigDecimal;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
public class LikeListDTO {
    private Integer sn;
    private String productName;
    private BigDecimal price;
    private BigDecimal feeRate;
    private Integer orderAmount;
    private String account;
    private BigDecimal totalAmount;
    private BigDecimal totalFee;
    private String email;

    // Constructor
    public LikeListDTO(Integer sn, String productName, BigDecimal price, BigDecimal feeRate,
                       Integer orderAmount, String account, BigDecimal totalAmount,
                       BigDecimal totalFee, String email) {
        this.sn = sn;
        this.productName = productName;
        this.price = price;
        this.feeRate = feeRate;
        this.orderAmount = orderAmount;
        this.account = account;
        this.totalAmount = totalAmount;
        this.totalFee = totalFee;
        this.email = email;
    }
}
