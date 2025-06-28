package com.example.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "LikeList")
public class LikeList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SN")
    private Integer sn;

    @Column(name = "OrderAmount", nullable = false)
    private Integer orderAmount;

    @NotBlank
    @Column(name = "Account", nullable = false)
    private String account;

    @DecimalMin("0.0")
    @Column(name = "TotalAmount", nullable = false)
    private BigDecimal totalAmount;

    @DecimalMin("0.0")
    @Column(name = "TotalFee", nullable = false)
    private BigDecimal totalFee;

    @ManyToOne
    @JoinColumn(name = "UserID", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "ProductNo", nullable = false)
    private Product product;
}
