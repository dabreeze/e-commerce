package com.phoenix.web.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
import com.phoenix.data.dto.ProductDto;
import com.phoenix.data.model.Product;
import com.phoenix.service.product.ProductService;
import com.phoenix.web.exceptions.BusinessLogicException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/product")
public class ProductRestController {

    @Autowired
    ProductService productService;

    @GetMapping()
    public ResponseEntity<?> findAllProducts(){

        List<Product> productList = productService.getAllProducts();

        return ResponseEntity.ok().body(productList);

    }


    @PostMapping("/create")
    public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto){

        try{
            Product savedProduct = productService.createProduct(productDto);
            return  ResponseEntity.ok().body(savedProduct);
        }catch (BusinessLogicException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @PatchMapping(path = "/{id}", consumes = "application/json-patch+json")
    public ResponseEntity<?> updateProduct(@PathVariable Long id,@RequestBody JsonPatch productPatch){

        try{
            Product updateProduct = productService.updateProductDetails(id, productPatch);
            return ResponseEntity.status(HttpStatus.OK).body(updateProduct);

        }catch (BusinessLogicException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }

    }


}
