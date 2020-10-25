package com.price.priceengineapi.controller;


import com.price.priceengineapi.modelDto.Product;
import com.price.priceengineapi.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/price")
public class PriceController {

    @Autowired
    private PriceService priceService;


    @GetMapping("/find_byprod_type/{qty}/{id}")
    public double findByProductType(@PathVariable int qty,@PathVariable int id){

        double price = priceService.findByProductType(qty,id);
        return price;
    }

    @GetMapping("/")
    public List<Product> priceList(){

        List<Product> priceList=priceService.priceList();

        return priceList;
    }

    @GetMapping("prodcut_list")
    public List<Product> productsList(){

        List<Product> productList=priceService.productsList();

        return productList;
    }

}
