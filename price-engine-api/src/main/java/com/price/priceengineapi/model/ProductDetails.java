package com.price.priceengineapi.model;

import lombok.Data;
import org.hibernate.annotations.Columns;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "product_details")
@Data
public class ProductDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    int productId;
    @Column(name = "name")
    String productName;
    @Column(name = "size")
    int cartonSize;
    @Column(name = "price")
    double crtoonPrice;

}
