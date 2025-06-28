package com.example.finance.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "Product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "No")
    private Integer no;

    @NotBlank
    @Column(name = "ProductName", nullable = false)
    private String productName;

    @DecimalMin("0.0")
    @Column(name = "Price", nullable = false)
    private BigDecimal price;

    @DecimalMin("0.0")
    @Column(name = "FeeRate", nullable = false)
    private BigDecimal feeRate;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<LikeList> likeLists;
}
