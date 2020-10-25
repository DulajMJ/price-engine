package com.price.priceengineapi.modelDto;

import com.price.priceengineapi.model.ProductDetails;
import lombok.Data;

import java.util.List;

@Data
public class Product {
    String productName;
    int productId;
    double unitPrice;
    List<ProductDetailsDto> productDetailsList;
}
