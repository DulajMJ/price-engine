package com.price.priceengineapi.repocitory;

import com.price.priceengineapi.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceRespocitory extends JpaRepository<ProductDetails,Integer> {


}
