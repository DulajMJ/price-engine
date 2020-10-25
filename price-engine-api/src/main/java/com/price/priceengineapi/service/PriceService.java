package com.price.priceengineapi.service;

import com.price.priceengineapi.modelDto.Product;

import java.math.BigDecimal;
import java.util.List;

public interface PriceService {

    double findByProductType(int qty, int id);

    List<Product> priceList();

    List<Product> productsList();
}
