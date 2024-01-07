package com.agarment.ProductService.controller;

import com.agarment.ProductService.entity.Product;
import com.agarment.ProductService.model.ProductRequest;
import com.agarment.ProductService.model.ProductResponse;
import com.agarment.ProductService.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    /**
     * addProduct method is used to create a product
     * @param productRequest
     * @return
     */
    @PreAuthorize("hasAuthority('Admin')")
    @PostMapping
    public ResponseEntity<Long> addProdcut(@RequestBody ProductRequest productRequest){
        long productId = productService.addProuct(productRequest);
        return new ResponseEntity<>(productId, HttpStatus.CREATED);
    }

    /**
     *
     * @param productId
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") long productId){
        productService.deleteProduct(productId);
        return new ResponseEntity<>("Product id "+productId+" deleted successfully...", HttpStatus.OK);
    }

    /**
     *
     * @param productId
     * @return
     */
    @PreAuthorize("hasAuthority('Admin') || hasAuthority('Customer') || hasAuthority('SCOPE_internal')")
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProduct(@PathVariable("id") long productId){
        ProductResponse productResponse = productService.getProduct(productId);
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    /**
     *
     * @return
     */
    @GetMapping()
    public ResponseEntity<List<ProductResponse>> getProducts(){
        List<ProductResponse> productResponse = productService.getProducts();
        return new ResponseEntity<>(productResponse, HttpStatus.OK);
    }

    /**
     *
     * @param productId
     * @param productRequest
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProdcut(@PathVariable("id") long productId, @RequestBody ProductRequest productRequest){
        ProductResponse productResponse = productService.updateProduct(productId, productRequest);
        return new ResponseEntity<>(productResponse, HttpStatus.CREATED);
    }

    /**
     *
     * @param productId
     * @param quantity
     * @return
     */
    @PutMapping("/reduceQuantity/{id}")
    ResponseEntity<Void> reduceQuantity(
            @PathVariable("id") long productId,
            @RequestParam long quantity
    ){

        productService.reduceQuantity(productId,quantity);
    return new ResponseEntity<>(HttpStatus.OK);
    }
}
