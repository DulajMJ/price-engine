package com.price.priceengineapi.modelDto;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

@Data
public class ProductDetailsDto {


    int productQty;
    double price;
}
