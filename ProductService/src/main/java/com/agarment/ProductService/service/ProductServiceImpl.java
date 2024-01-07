package com.agarment.ProductService.service;

import com.agarment.ProductService.entity.Product;
import com.agarment.ProductService.error.ProductServiceException;
import com.agarment.ProductService.model.ProductRequest;
import com.agarment.ProductService.model.ProductResponse;
import com.agarment.ProductService.repository.ProductRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.beans.BeanUtils.*;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService{
    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProuct(ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .build();
        copyProperties(productRequest, product);
        log.info("Product before save : "+product.toString());
        productRepository.save(product);
        log.info("product saved..."+product.toString());
        return product.getProductId();
    }

    @Override
    public long deleteProduct(long productId) {
        productRepository.deleteById(productId);
        log.info("Product with id is deleted successfully : "+productId);
        return productId;
    }

    @Override
    public ProductResponse getProduct(long productId) {
       Product product = productRepository.findById(productId).orElseThrow(()->new ProductServiceException("Product not found", "PRODUCT_NOT_FOUND"));
       ProductResponse productResponse = new ProductResponse();
       copyProperties(product, productResponse);
       return productResponse;
    }

    @Override
    public List<ProductResponse> getProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductResponse> productResponses = products.stream().map(product -> {
            ProductResponse productResponse = new ProductResponse();
            copyProperties(product,productResponse);
            return productResponse;
        }).collect(Collectors.toList());
        return productResponses;
    }

    @Override
    public ProductResponse updateProduct(long productId, ProductRequest productRequest) {
        Product product = Product.builder()
                .productName(productRequest.getName())
                .quantity(productRequest.getQuantity())
                .price(productRequest.getPrice())
                .productId(productId)
                .build();
        ProductResponse productResponse = new ProductResponse();
        productRepository.save(product);
        copyProperties(product, productResponse);

        log.info("product updated..."+product.toString());
        return productResponse;
    }

    @Override
    public void reduceQuantity(long productId, long quantity) {
        log.info("Reduce quanity {} of product id {}",quantity, productId);
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductServiceException("Product with given id not found","PRODUCT NOT FOUND"));
        if(product.getQuantity() < quantity){
            throw new ProductServiceException("Insufficient quanity which place irder","INSUFFICIENT QUANITY");
        }
        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
        log.info("quanity reduced successfully");

    }

}
