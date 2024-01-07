package com.agarment.ProductService.service;

import com.agarment.ProductService.model.ProductRequest;
import com.agarment.ProductService.model.ProductResponse;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    long addProuct(ProductRequest productRequest);

    long deleteProduct(long productId);

    ProductResponse getProduct(long productId);

    List<ProductResponse> getProducts();

    ProductResponse updateProduct(long productId, ProductRequest productRequest);

    void reduceQuantity(long productId, long quantity);
}
