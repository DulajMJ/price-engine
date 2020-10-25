package com.price.priceengineapi.service;

import com.price.priceengineapi.Config.Config;
import com.price.priceengineapi.model.ProductDetails;
import com.price.priceengineapi.modelDto.Product;
import com.price.priceengineapi.modelDto.ProductDetailsDto;
import com.price.priceengineapi.repocitory.PriceRespocitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PriceServiceImpl implements PriceService{

    @Autowired
    PriceRespocitory priceRespocitory;

    @Override
    public double findByProductType(int qty, int id) {

        Optional<ProductDetails> productDetails = searchProduct(id);

        int crtoonSize=productDetails.get().getCartonSize();
        double dbcrtoonPrice=productDetails.get().getCrtoonPrice();

        if(productDetails.isPresent()){

            return productPriceGenerator(qty, crtoonSize, dbcrtoonPrice);
        }else{

            return 0.0;
        }

    }


    @Override
    public List<Product> priceList() {
        //new arrya list and object
        List<Product> productList=new ArrayList<>();
        List<ProductDetailsDto> productDetailsDtoList = new ArrayList<>();
        //search query
        List<ProductDetails> allProdctList =priceRespocitory.findAll();
        //
        allProdctList.forEach((get)->{

            Product product=new Product();
            product.setProductName(get.getProductName());
            product.setProductId(get.getProductId());
            product.setUnitPrice(0.0);

            int crtoonSize=get.getCartonSize();
            double dbcrtoonPrice=get.getCrtoonPrice();
            product.setUnitPrice(unitPrice(crtoonSize,dbcrtoonPrice));

            for(int qty=1;qty<=50;qty++){

                ProductDetailsDto productDetailsDto=new ProductDetailsDto();

                double productPrice=productPriceGenerator(qty,crtoonSize,dbcrtoonPrice);

                productDetailsDto.setPrice(productPrice);
                productDetailsDto.setProductQty(qty);
                productDetailsDtoList.add(productDetailsDto);

            }
            product.setProductDetailsList(productDetailsDtoList);

            productList.add(product);

        });

        return productList;
    }

    @Override
    public List<Product> productsList() {

        List<Product> productList=new ArrayList<>();

        List<ProductDetails> allProdctList =priceRespocitory.findAll();

        allProdctList.forEach((get)->{

            Product product=new Product();
            product.setProductName(get.getProductName());
            product.setProductId(get.getProductId());
            productList.add(product);
        });

        return productList;
    }

    private Optional<ProductDetails> searchProduct(int id) {
        return priceRespocitory.findById(id);
    }

    private double productPriceGenerator(int qty, int crtoonSize, double dbcrtoonPrice) {

        int unit=qty%crtoonSize;
        int cartonQty=(qty-unit)/crtoonSize;

        double crtoonPrice=0.0;
        double unitPrices=0.0;

        if(cartonQty>Config.DISCOUNT_QTY){

            double discuntPrice=discount(cartonQty,dbcrtoonPrice);
            crtoonPrice=dbcrtoonPrice*cartonQty-discuntPrice;

        }else{
            crtoonPrice=(dbcrtoonPrice*cartonQty);

        }

        if(unit>Config.UNIT_COUNT){

            double unitPrice=unitPrice(crtoonSize,dbcrtoonPrice);


            unitPrices=unitPrice*unit;

        }

        return unitPrices+crtoonPrice;
    }

    private double discount(int cartonQty, double dbcrtoonPrice){

        return (dbcrtoonPrice*cartonQty)*Config.DISCOUNT_PRICE;
    }
    private double unitPrice(int cartonQty, double dbcrtoonPrice){

        return (dbcrtoonPrice/cartonQty)*Config.COMPENSATE_PRICE;
    }
}
